package common.check.results;

/**
 * this interface is implemented by all Check results.
 * @author hdaiv_000
 *
 */
public interface I_CheckResult {
	/**
	 * this method is used to access the Difference between the given threshold and the checks result
	 * @return an Integer >=0 if the check was passed and &lt;0 if the check was missed
	 */
	public abstract int getResultDifference();

	/**
	 * return a bool describing the checks result
	 * @return true if the check was passed false if not
	 */
	public abstract boolean isPassed();

	/**
	 * checks if it is a crit should not need to be used
	 * @return true if crit
	 */
	public abstract boolean isCrit();

	/**
	 * checks if it is a fumble should not need to be used
	 * @return true if fumble
	 */
	public abstract boolean isFumble();
}
