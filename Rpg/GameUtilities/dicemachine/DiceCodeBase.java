package dicemachine;

import common.decorator.DecoratorBase;

public abstract class DiceCodeBase extends DecoratorBase<I_DiceCode, Integer> implements I_DiceCode{

	protected final boolean negative;

	public DiceCodeBase(boolean negative) {
		super();
		this.negative = negative;
	}

	public int getMax() {
		int sum = 0;
		for(I_DiceCode e : this)
			sum += e.max();
		return sum;
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

		return this.negative == ((DiceCodeBase)obj).negative;
	}

	@Override
	public int hashCode() {
		return Boolean.hashCode(this.negative);
	}
}
