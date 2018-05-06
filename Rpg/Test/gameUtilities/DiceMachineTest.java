package gameUtilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

	@Test
	void testGetRollwithRoll() {
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y -> {
				I_DiceCode die = DiceCodeBase.roll(x, y);

				I_DiceCode other = DiceCodeBase.roll(x, y);

				die.addDecorator(other);

				int erg = die.get();

				boolean toTest = erg >= 2 * (x) && erg <= (x*y) * 2;

				assertTrue(toTest);
			}));
	}

	@Test
	void testToStringPositiveDoubleRoll() {
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y -> {
				I_DiceCode die = DiceCodeBase.roll(x, y);

				I_DiceCode other = DiceCodeBase.roll(x, y);

				die.addDecorator(other);

				String exp = String.valueOf(x).concat("d").concat(String.valueOf(y));
				exp = exp + " + " + exp;
				assertEquals(exp, die.toString());
		}));
	}

	@Test
	void testToStringNegativeDoubleRoll() {
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y -> {
				I_DiceCode die = DiceCodeBase.roll(x, y);

				I_DiceCode other = DiceCodeBase.roll(x, y, true);

				die.addDecorator(other);

				String exp = String.valueOf(x).concat("d").concat(String.valueOf(y));
				exp = exp + " - " + exp;
				assertEquals(exp, die.toString());
		}));
	}

	@Test
	void testToStringRollRollMod() {
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y ->
				IntStream.rangeClosed(-30, 30).forEach(z ->{
					I_DiceCode die = DiceCodeBase.roll(x, y);

					I_DiceCode other = DiceCodeBase.roll(x, y);

					I_DiceCode mod = DiceCodeBase.flat(z);

					die.addDecorator(other);
					die.addDecorator(mod);

					String exp = String.valueOf(x).concat("d").concat(String.valueOf(y));
					exp = exp + " + " + exp;
					exp = exp +
							String.valueOf(mod.get() < 0 ? " - " : " + " ) +
							String.valueOf(mod.get() < 0 ? mod.get() * -1 : mod.get());
					assertEquals(exp, die.toString());
		})));
	}
}
