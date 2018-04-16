package test;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import boxes.Pair;
import dicemachine.DiceMachine;
import logging.ConsoleLogger;
import logging.I_Logger;

@SuppressWarnings("javadoc")
public class DiceTest {

	public static void main(String[] args) {
		DiceMachine dc = new DiceMachine();

		I_Logger ll = new ConsoleLogger();
		ll.setComplete(true);

		IntStream.range(0, 1000)
			.mapToObj(e -> (Integer)Integer.parseInt(""+dc.getRoll("2d6 +1")))
			.collect(Collectors.groupingBy(Function.identity()))
			.values().stream()
			.map(e -> new Pair<>(e.size(), e.get(0)))
			.sorted(Comparator.comparingInt(p -> p.getValue()))
			.forEach(l ->
				ll.complete(
					()-> l.toString()
				)
			);
	}

}
