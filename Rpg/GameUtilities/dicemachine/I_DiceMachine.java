package dicemachine;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Interface implemented by DiceMachines. That is Machines that convert diceCodes into Integers.
 * <code><br>
 * <b>Dicecode</b><br>
 * xdy-+z with:<br>
 * x numbers of dice<br>
 * y sides of a die<br>
 * z a number to add/subtract
 * </code>
 * @author Dave
 *
 */
@FunctionalInterface
public interface I_DiceMachine extends Serializable{

	/**
	 * this method is used to analyze the dicecode and compute the actual integer
	 * @param diceCode the dicecode for your throw
	 * @return the integer that is a result of the given dicecode
	 */
	public abstract int getRoll(String diceCode);

	/**
	 * Utility Method used by the {@link I_DiceMachine} implementation to split the dicecode into its component parts
	 * @param diceCode the code used to describe the dicethrow
	 * @param delim the delimiter used by the {@link StringTokenizer}.
	 * @return a array of two strings
	 */
	public default String[] split(String diceCode, String delim) {
		StringTokenizer strk = new StringTokenizer(diceCode, delim);
		String[] arr = new String[strk.countTokens()];
		for(int i = 0; i < arr.length; i++)
			arr[i] = strk.nextToken();
		return arr;
	}
}
