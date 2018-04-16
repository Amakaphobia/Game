package logging;

import java.util.function.Supplier;

public interface I_Logger {	
	public abstract void complete(Supplier<String> msgGenerator);
	public abstract void debug(Supplier<String> msgGenerator);
	public abstract void less(Supplier<String> msgGenerator);
	public abstract void setComplete(boolean value);
	public abstract void setDebug(boolean value);
	public abstract void setLess(boolean value);
}
