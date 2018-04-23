package entity.basic.common;

/**
 * This Interface is Implemented by All decorator classes. It Provides support for adding and removing more of the
 * same and treating the group as a single thing
 * @author Dave
 *
 * @param <S> The Type of the Object Itself
 * @param <R> the ReturnValue of the getValue method
 */
public interface I_SimpleDecorator <S, R> extends Iterable<S>{
	/**
	 * this method is used to add a decorator to this Decorator
	 * @param other the Decorator you want to add
	 */
	public abstract void addDecorator(S other);
	/**
	 * this method is used to remove a decorator to this decorator
	 * @param other the decorator you want to remove
	 */
	public abstract void removeFirstDecorator(S other);

	/**
	 * Used to access the decorator of this object
	 * @return the decorator
	 */
	public abstract S getDecorator();

	/**
	 * return the level of this I_SimpleDecorator as a R
	 * @return the level of this I_SimpleDecorator
	 */
	public abstract R getValue();


}
