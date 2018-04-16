package logging;

import java.util.function.Supplier;

public class ConsoleLogger implements I_Logger {
	
	private static boolean doComplete = false;
	private static boolean doDebug = false;
	private static boolean doLess = false;

	
	private void log(String s) {
		System.out.println(s);
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
