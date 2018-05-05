package dicemachine;

import common.decorator.DecoratorBase;

public abstract class DiceCodeBase
		extends DecoratorBase<I_DiceCode, Integer>
		implements I_DiceCode {

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
	public int max() {
		int sum = 0;
		for(I_DiceCode e : this)
			if(e instanceof DiceCodeBase)
				sum += ((DiceCodeBase)e).getMaxPersonal();
		return sum;
	}

	protected abstract int getMaxPersonal();

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

		return this.negative == ((DiceCodeBase)obj).negative;
	}

	@Override
	public int hashCode() {
		return Boolean.hashCode(this.negative);
	}
}
