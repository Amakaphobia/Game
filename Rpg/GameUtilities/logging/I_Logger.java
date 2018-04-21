package logging;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * This interface is implemented by all loggers. It is lazy.<br>
 *
 * @author Dave
 *
 */
public interface I_Logger extends Serializable{
	/**
	 * Used to access logging at the complete level
	 * @param msgGenerator A {@link Supplier} that supplies the String to log.
	 */
	public abstract void complete(Supplier<String> msgGenerator);
	/**
	 * Used to access logging at the debug level
	 * @param msgGenerator A {@link Supplier} that supplies the String to log.
	 */
	public abstract void debug(Supplier<String> msgGenerator);
	/**
	 * Used to access logging at the less level
	 * @param msgGenerator A {@link Supplier} that supplies the String to log.
	 */
	public abstract void less(Supplier<String> msgGenerator);
	/**
	 * used to set the complete value to a given value
	 * @param value what to say
	 */
	public abstract void setComplete(boolean value);
	/**
	 * used to set the debug value to a given value
	 * @param value what to say
	 */
	public abstract void setDebug(boolean value);
	/**
	 * used to set the less value to a given value
	 * @param value what to say
	 */
	public abstract void setLess(boolean value);
}
