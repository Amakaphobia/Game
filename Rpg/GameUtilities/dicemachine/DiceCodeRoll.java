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
}
