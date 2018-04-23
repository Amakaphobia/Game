package entity.basic.common;

import java.util.Iterator;

/**
 * This is a Base implementation for Decorators it handles all logic that is required by the {@link I_SimpleDecorator}
 * that can be handled here. It also provides the Iterator implementation
 *
 * @author Dave
 *
 * @param <S> The Type of the Object Itself
 * @param <R> the ReturnValue of the getValue method
 */
public abstract class DecoratorBase<S extends I_SimpleDecorator<S, R>, R> implements I_SimpleDecorator<S, R>{

	//Decorator

	/**Holds the Decorator Attribute for this Attribute*/
	protected S Decorator;

	@Override
	public S getDecorator() { return this.Decorator; }
	@Override
	public void addDecorator(S other) {
		if(this.Decorator == null)
			this.Decorator = other;
		else
			this.Decorator.addDecorator(other);
		this.onAfterAdding();
	}
	@Override
	public void removeFirstDecorator(S other) {
		if(this.Decorator == null) return;

		if(this.Decorator.equals(other))
			this.Decorator = other.getDecorator();
		else
			this.Decorator.removeFirstDecorator(other);
		this.onAfterRemoving();
	}

	/**
	 * This Method does nothing but is an easy way for the implementation to do something everytime a decorator gets
	 * added
	 */
	protected void onAfterAdding() {}
	/**
	 * This Method does nothing but is an easy way for the implementation to do something everytime a decorator gets
	 * removed
	 */
	protected void onAfterRemoving() {}

	/**Constructor*/
	public DecoratorBase() {
		super();
	}

	//Iterator Handling

	@Override
	public Iterator<S> iterator() {
		return new I_SimpleDecoratorIterator();
	}

	/**
	 * Simple Iterator implementation to handle iterating over all decorators of this item
	 * @author Dave
	 *
	 */
	protected class I_SimpleDecoratorIterator implements Iterator<S>{
		/**the current Decorator*/
		private I_SimpleDecorator<S, R> current = DecoratorBase.this;
		/**flag to remember if the first element was send down*/
		private boolean first = true;

		@Override
		public boolean hasNext() {
			if(first)
				return true;

			return this.current.getDecorator() != null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public S next() {
			if(first) {
				first = false;
				return (S)DecoratorBase.this;
			}

			S ret = current.getDecorator();

			current = current.getDecorator();

			return ret;
		}

	}

}
