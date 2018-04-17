package logging;

import java.util.function.Supplier;

/**
 * Simple {@link I_Logger} implementation. It logs events to console.
 * @author Dave
 *
 */
public class ConsoleLogger implements I_Logger {

	/**used to describe complete logging state*/
	private static boolean doComplete = false;
	/**used to describe heightened logging state*/
	private static boolean doDebug = false;
	/**used to describe lowered logging state*/
	private static boolean doLess = false;

	/**
	 * internal method used to actually log the string
	 * @param toLog the String you want to log
	 */
	private void log(String toLog) {
		System.out.println(toLog);
	}

	@Override
	public void complete(Supplier<String> msgGenerator) {
		if(!doComplete) return;
		this.log(msgGenerator.get());
	}

	@Override
	public void debug(Supplier<String> msgGenerator) {
		if(!doDebug) return;
		this.log(msgGenerator.get());
	}

	@Override
	public void less(Supplier<String> msgGenerator) {
		if(!doLess) return;
		this.log(msgGenerator.get());
	}

	@Override
	public void setComplete(boolean value) {
		doComplete = value;
		this.setDebug(value);
	}

	@Override
	public void setDebug(boolean value) {
		doDebug = value;
		this.setLess(value);
	}

	@Override
	public void setLess(boolean value) {
		doLess = value;
	}

}
