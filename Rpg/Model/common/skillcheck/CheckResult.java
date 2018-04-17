package common.skillcheck;

/**
 * this class gets returned by Skill attribute checks and so on
 * @author hdaiv_000
 *
 */
public class CheckResult implements I_CheckResult {
	/**the difference between the result and the threshold*/
	private int resultDiff;
	
	/**
	 * Constructor
	 * @param resultDiff the difference between throw result and threshold
	 */
	public CheckResult(int resultDiff) {
		this.resultDiff = resultDiff;
	}

	@Override
	public int getResultDifference() { return this.resultDiff; }

	@Override
	public boolean isPassed() { return this.resultDiff >= 0; }

}
