package dicemachine;

import common.decorator.I_SimpleDecorator;

public interface I_DiceCode extends I_SimpleDecorator<I_DiceCode, Integer>, Cloneable {

	public abstract int get();

	public abstract int max();

	public static DiceCodeBase flat(int flat, boolean negative) { return new DiceCodeFlat(flat, negative); }

	public static DiceCodeBase flat(int flat) {
		return I_DiceCode.flat(flat, false);
	}

	public static DiceCodeBase roll(int count, int size, boolean negative) {
		return new DiceCodeRoll(count, size, negative);
	}
	public static DiceCodeBase roll(int count, int size) {
		return I_DiceCode.roll(count, size, false);
	}

	public default I_DiceCode add(int flat) {
		this.addDecorator(I_DiceCode.flat(flat, false));
		return this;
	}

	public default I_DiceCode add(int count, int size) {
		this.addDecorator(I_DiceCode.roll(count, size, false));
		return this;
	}

	public default I_DiceCode subtract(int flat) {
		this.addDecorator(I_DiceCode.flat(flat, true));
		return this;
	}

	public default I_DiceCode subtract(int count, int size) {
		this.addDecorator(I_DiceCode.roll(count, size, true));
		return this;
	}
}
