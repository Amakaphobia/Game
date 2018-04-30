package common.decorator;

/**
 * This is a very Simple Implementation of the {@link DecoratorBase} Class. <br>
 * it holds an Integer and is able to iterate over all decorated integers and add them up. it is intended for modifier
 * use.
 *
 * @author Dave
 *
 * @see DecoratorBase
 */
public class SimpleIntegerDecorator extends DecoratorBase<SimpleIntegerDecorator, Integer> {

	/**holds the modifier*/
	private int modifier;
	/**@param modifier the modifier to set*/
	public void setModifier(int modifier) { this.modifier = modifier; }

	/**
	 * This Method adds all Modifiers that are decorated with this modifier up
	 */
	@Override
	public Integer getValue() {
		int sum = 0;
		for(SimpleIntegerDecorator e : this)
			sum += e.modifier;
		return sum;
	}

	/**
	 * Constructor
	 * @param modifier the modifier of this object
	 */
	public SimpleIntegerDecorator(int modifier) {
		this.modifier = modifier;
	}



}