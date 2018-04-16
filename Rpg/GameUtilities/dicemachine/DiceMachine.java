package dicemachine;

import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * This Class is a Simple {@link I_DiceMachine} implementation. it is used to generate a single integer +
 * out of a dicecode in the form of <br>
 * <code>
 * xdy-+z with:<br>
 * x numbers of dice<br>
 * y sides of a die<br>
 * z a number to add/subtract
 * </code>
 * @author Dave
 */
public class DiceMachine implements I_DiceMachine{
	/**holds the {@link Random} object that generates random Numbers*/
	private Random dice = new Random();

	@Override
	public int getRoll(String diceCode) {
		IntBinaryOperator combiner = null;;
		String[] codeParts = new String[2];
		String diceCodeTrimmed = diceCode.trim();
		try {
			return Integer.parseInt(diceCodeTrimmed);
		}catch(NumberFormatException e) {}

		if(diceCodeTrimmed.contains("-")) {
			combiner = (a, b) -> a - b;
			codeParts = this.split(diceCodeTrimmed, "-");
		} else if(diceCodeTrimmed.contains("+")) {
			combiner = (a, b) -> a + b;
			codeParts = this.split(diceCodeTrimmed, "+");
		} else if(diceCodeTrimmed.contains("d")) {
			codeParts = this.split(diceCodeTrimmed, "d");
			final int wuerfelZahl = this.getRoll(codeParts[0]);
			final int wuerfelSeite = this.getRoll(codeParts[1]);

			return
				IntStream.range(0, wuerfelZahl)
					.map(e -> this.dice.nextInt(wuerfelSeite) + 1)
					.sum();
		}

		return combiner.applyAsInt(
				this.getRoll(codeParts[0]),
				this.getRoll(codeParts[1]));
	}


}
