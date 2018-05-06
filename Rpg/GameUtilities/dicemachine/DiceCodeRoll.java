package dicemachine;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * This Class is used for dynamic dice rolls. it rolls a number of equal die and sums them up.
 * @author Dave
 *
 */
public class DiceCodeRoll extends DiceCodeBase{

	/**this dice count*/
	private int diceCount;
	/**the dice size*/
	private int diceSize;
	/**true if negative*/
	private boolean negative;

	/**used to roll the dice*/
	private static final Random RANDOM = new Random();

	/**
	 * Hidden constructor
	 * @param diceCount the dice count
	 * @param diceSize the dice size
	 * @param negative true if negative
	 */
	DiceCodeRoll(int diceCount, int diceSize, boolean negative) {
		super();
		this.diceCount = diceCount;
		this.diceSize = diceSize;
		this.negative = negative;
	}

	@Override
	protected int getPersonal() {
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
	protected int getMaxPersonal() {
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

		if(this.negative)
			strb.append("-");

		strb.append(this.diceCount)
			.append("d")
			.append(this.diceSize);

		if(this.hasDecorator()) {
			String next = this.getDecorator().toString();
			if(next.startsWith("-"))
				strb.append(next.replaceFirst("-", " - "));
			else
				strb.append(" + ")
					.append(next);
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
		int diceSizeHc = Integer.hashCode(this.diceSize);
		int diceCountHc = Integer.hashCode(this.diceCount);

		return diceSizeHc * diceSizeHc +
				diceCountHc * diceCountHc * diceCountHc;
	}
}
