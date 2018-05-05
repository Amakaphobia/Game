package dicemachine;

import java.util.Random;
import java.util.stream.IntStream;

@SuppressWarnings("javadoc")
public class DiceCodeRoll extends DiceCodeBase{

	private int diceCount;
	private int diceSize;

	private static final Random RANDOM = new Random();

	public DiceCodeRoll(int diceCount, int diceSize, boolean negative) {
		super(negative);
		this.diceCount = diceCount;
		this.diceSize = diceSize;
	}

	@Override
	public int get() {
		int erg =
			IntStream.range(0, this.diceCount)
				.map(i -> DiceCodeRoll.RANDOM.nextInt(this.diceSize) + 1)
				.sum();
		return
			this.negative ?
			-erg :
			 erg;
	}

	@Override
	public int max() {
		int erg =
				IntStream.range(0, this.diceCount)
					.map(i -> this.diceSize)
					.sum();
			return
				this.negative ?
				-erg :
				 erg;
	}

	//obj

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();

		strb.append(this.diceCount)
			.append("d")
			.append(this.diceSize);

		if(this.hasDecorator()) {
			DiceCodeBase other = (DiceCodeBase) this.getDecorator();
			strb
				.append(other.negative ? "-" : "+" )
				.append(other.toString());
		}

		return strb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof DiceCodeRoll)) return false;

		DiceCodeRoll other = (DiceCodeRoll)obj;
		return this.diceSize == other.diceSize
			&&  this.diceCount == other.diceCount
			&&  super.equals(other);
	}

	@Override
	public int hashCode() {
		int negativeHc = super.hashCode();
		int diceSizeHc = Integer.hashCode(this.diceSize);
		int diceCountHc = Integer.hashCode(this.diceCount);

		return negativeHc +
				diceSizeHc * diceSizeHc +
				diceCountHc * diceCountHc * diceCountHc;
	}
}
