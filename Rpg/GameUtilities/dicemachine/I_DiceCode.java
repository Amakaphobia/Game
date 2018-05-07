package dicemachine;

import common.decorator.I_SimpleDecorator;

/**
 * This is the base interface for Dice rolls.
 *
 * @see Cloneable
 * @see I_SimpleDecorator
 *
 * @author Dave
 */
public interface I_DiceCode extends I_SimpleDecorator<I_DiceCode, Integer>, Cloneable {

	/**
	 * this method is used to get the value of this dice roll
	 * @return the value of the roll
	 */
	@Override
	public abstract Integer getValue();

	/**
	 * this method is used to get the maximum value of this dice roll
	 * @return the highest possible value of the roll
	 */
	public abstract int maxValue();

	@SuppressWarnings("javadoc")
	public abstract I_DiceCode clone() throws CloneNotSupportedException;

	/**
	 * this method is used to add a modifier to this dice roll
	 * @param flat the number you want to add
	 * @return this for chain use or a new immutable {@link I_DiceCode}
	 */
	public abstract I_DiceCode addModifier(int flat);

	/**
	 *  this method is used to add another die to this dice roll
	 * @param count the number of dice
	 * @param size the size of dice
	 * @return this for chain use or a new immutable {@link I_DiceCode}
	 */
	public abstract I_DiceCode addModifier(int count, int size);

	/**
	 *  this method is used to subtract another die from this dice roll
	 * @param count the number of dice
	 * @param size the size of dice
	 * @return this for chain use or a new immutable {@link I_DiceCode}
	 */
	public abstract I_DiceCode subtractModifier(int count, int size);

	/**
	 * This default method is used to check if you rolled the highest possible number on a die
	 * @return true if crit false if not
	 */
	public abstract boolean isCrit();
	/**
	 * This Method is used to check if you rolled a 1
	 * @return true if 1
	 */
	public abstract boolean isFumble();
}
