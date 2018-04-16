package dicemachine;

import java.util.StringTokenizer;

@FunctionalInterface
public interface I_DiceMachine {
	public abstract int getRoll(String diceCode);
	
	public default String[] split(String s, String delim) {
		StringTokenizer strk = new StringTokenizer(s, delim);
		String[] arr = new String[strk.countTokens()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = strk.nextToken();
		}
		return arr;
	}
}
