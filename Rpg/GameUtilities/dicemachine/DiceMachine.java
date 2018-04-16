package dicemachine;

import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class DiceMachine implements I_DiceMachine{
	private Random dice = new Random();
	
	public DiceMachine() {
		super();
	}
	
	@Override
	public int getRoll(String diceCode) {
		IntBinaryOperator combiner = null;;
		String[] codeParts = new String[2];
		
		try {
			return Integer.parseInt(diceCode);			
		}catch(NumberFormatException e) {}
		
		if(diceCode.contains("-")) {
			combiner = (a, b) -> a - b;
			codeParts = this.split(diceCode, "-");
		} else if(diceCode.contains("+")) {
			combiner = (a, b) -> a + b;
			codeParts = this.split(diceCode, "+");
		} else if(diceCode.contains("d")) {
			codeParts = this.split(diceCode, "d");
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
