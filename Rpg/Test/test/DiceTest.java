package test;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dicemachine.DiceMachine;
import logging.ConsoleLogger;
import logging.I_Logger;

public class DiceTest {

	public static void main(String[] args) {
		DiceMachine dc = new DiceMachine();
		
		I_Logger ll = new ConsoleLogger();
		ll.setComplete(true);
		
		IntStream.range(0, 100)
			.mapToObj(e -> (Integer)Integer.parseInt(""+dc.getRoll("1d5 + 2")))
			.sorted(Comparator.comparingInt(e -> e))
			.collect(Collectors.groupingBy(Function.identity()))
//			.values().stream()
//			.mapToInt(l -> l.size())
			.entrySet()
			.forEach(l -> 
				ll.complete(
					()-> l.toString()
				)
			);
	}

}
