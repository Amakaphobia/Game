package dicemachine;

import common.decorator.DecoratorBase;

public abstract class DiceCodeBase extends DecoratorBase<I_DiceCode, Integer> implements I_DiceCode{

	protected final boolean negative;

	public DiceCodeBase(boolean negative) {
		super();
		this.negative = negative;
	}

	@Override
	public Integer getValue() {
		int sum = 0;
		for(I_DiceCode e : this)
			sum += e.get();
		return sum;
	}
}
