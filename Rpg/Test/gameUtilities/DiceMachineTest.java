package gameUtilities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import dicemachine.DiceCodeBase;
import dicemachine.I_DiceCode;

@SuppressWarnings("javadoc")
class DiceMachineTest {

	@Test
	void testGetRoll() {
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y -> {
				I_DiceCode die = DiceCodeBase.roll(x, y);
				int erg = die.get();
				boolean toTest = erg >= x && erg <= x*y;

				assertTrue(toTest);
			})
		);
	}

	@Test
	void testGetRollMax() {
		IntStream.rangeClosed(1, 1000).forEach(x ->
			IntStream.rangeClosed(1, 1000).forEach(y -> {
				I_DiceCode die = DiceCodeBase.roll(x, y);
				int erg = die.max();
				boolean toTest = erg == x*y;

				assertTrue(toTest);
			})
		);
	}

	@Test
	void testGetRollwithAdd() {
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y ->
				IntStream.rangeClosed(-20, 20).forEach(z -> {
					char op = z < 0 ? '-' : '+';
					I_DiceCode die = DiceCodeBase.roll(x, y);

					I_DiceCode other = DiceCodeBase.flat(z);

					die.addDecorator(other);

					int erg = die.get();

					boolean toTest = erg >= x+z && erg <= x*y+z;

					assertTrue(toTest);
				})
			)
		);
	}
}
