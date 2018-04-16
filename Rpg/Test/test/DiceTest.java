package test;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dicemachine.DiceMachine;

public class DiceTest {

	public static void main(String[] args) {
		DiceMachine dc = new DiceMachine();
		
		IntStream.range(0, 100)
			.mapToObj(e -> (Integer)Integer.parseInt(""+dc.getRoll("1d5")))
			.sorted(Comparator.comparingInt(e -> e))
			.collect(Collectors.groupingBy(Function.identity()))
			.values().stream()
			.mapToInt(l -> l.size())
			.forEach(System.out::println);
	}

}
