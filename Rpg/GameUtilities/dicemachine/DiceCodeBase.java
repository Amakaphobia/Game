package dicemachine;

import common.decorator.DecoratorBase;

/**
 * This is the BaseImplementation for simple DiceCodes.
 * @author Dave
 *
 * @see DecoratorBase
 * @see I_DiceCode
 *
 */
public abstract class DiceCodeBase
		extends DecoratorBase<I_DiceCode, Integer>
		implements I_DiceCode {

	/**Hidden Constructor*/
	protected DiceCodeBase() {
		super();
	}

	/**
	 * gets the max Value for this dice roll
	 * @return the maximum value
	 */
	public int getMax() {
		int sum = 0;
		for(I_DiceCode e : this)
			sum += e.max();
		return sum;
	}

	/**@return the maximum value for this level of the dice code*/
	protected abstract int getMaxPersonal();
	/**@return the value for this level of the dice code*/
	protected abstract int getPersonal();

	/**
	 * Method used to build a flat Dice Code
	 * @param flat the value
	 * @return the {@link DiceCodeFlat}
	 */
	public static I_DiceCode flat(int flat) {
		return new DiceCodeFlat(flat);
	}
	/**
	 * Method used to build a dynamic Dice Code
	 * @param count the dice count
	 * @param size the dice size
	 * @param negative true if negative
	 * @return the {@link DiceCodeRoll}
	 */
	public static I_DiceCode roll(int count, int size, boolean negative) {
		return new DiceCodeRoll(count, size, negative);
	}

	/**
	 * Method used to build a dynamic Dice Code
	 * @param count the dice count
	 * @param size the dice size
	 * @return the {@link DiceCodeRoll}
	 */
	public static I_DiceCode roll(int count, int size) {
		return DiceCodeBase.roll(count, size, false);
	}

	@Override
	public I_DiceCode addModifier(int flat) {
		this.addDecorator(DiceCodeBase.flat(flat));
		return this;
	}

	@Override
	public I_DiceCode addModifier(int count, int size) {
		this.addDecorator(DiceCodeBase.roll(count, size, false));
		return this;
	}

	@Override
	public I_DiceCode subtractModifier(int count, int size) {
		this.addDecorator(DiceCodeBase.roll(count, size, true));
		return this;
	}

	@Override
	public int max() {
		int sum = 0;
		for(I_DiceCode e : this)
			if(e instanceof DiceCodeBase)
				sum += ((DiceCodeBase)e).getMaxPersonal();
		return sum;
	}

	@Override
	public int get() {
		int sum = 0;
		for(I_DiceCode e : this)
			if(e instanceof DiceCodeBase)
				sum += ((DiceCodeBase)e).getPersonal();
		return sum;
	}

	@Override
	public I_DiceCode clone() throws CloneNotSupportedException {
		DiceCodeBase clone = (DiceCodeBase)super.clone();
		return clone;
	}

	@Override
	public Integer getValue() {
		int sum = 0;
		for(I_DiceCode e : this)
			sum += e.get();
		return sum;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof DiceCodeBase)) return false;

		return true;
	}
}
