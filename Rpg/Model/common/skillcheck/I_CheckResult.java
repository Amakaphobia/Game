package common.skillcheck;

/**
 * this interface is implemented by all Checkresults. 
 * @author hdaiv_000
 *
 */
public interface I_CheckResult {
	/**
	 * this method is used to access the Difference between the given threshold and the checks result
	 * @return an Integer >=0 if the check was passedand &lt;0 if the check was missed  
	 */
	public abstract int getResultDifference();
	
	/**
	 * return a bool descriing the checks result
	 * @return true if the check was passed false if not
	 */
	public abstract boolean isPassed();
}
