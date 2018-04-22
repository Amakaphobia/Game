package gameUtilities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import dicemachine.DiceMachine;
import dicemachine.I_DiceMachine;

@SuppressWarnings("javadoc")
class DiceMachineTest {

	@Test
	void testGetRoll() {
		I_DiceMachine dm = new DiceMachine();
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y -> {
				int erg = dm.getRoll(x +"d"+y);
				boolean toTest = erg >= x && erg <= x*y;

				assertTrue(toTest);
			})
		);
	}

	@Test
	void testGetRollMax() {
		I_DiceMachine dm = new DiceMachine();
		IntStream.rangeClosed(1, 1000).forEach(x ->
			IntStream.rangeClosed(1, 1000).forEach(y -> {
				int erg = dm.getRollMax(x +"d"+y);
				boolean toTest = erg == x*y;

				assertTrue(toTest);
			})
		);
	}

	@Test
	void testGetRollwithAdd() {
		I_DiceMachine dm = new DiceMachine();
		IntStream.rangeClosed(1, 100).forEach(x ->
			IntStream.rangeClosed(1, 100).forEach(y ->
				IntStream.rangeClosed(-20, 20).forEach(z -> {
					char op = z < 0 ? '-' : '+';
					int erg = dm.getRoll(x +"d"+y +op +z);
					boolean toTest = erg >= x+z && erg <= x*y+z;

					assertTrue(toTest);
				})
			)
		);
	}
}
