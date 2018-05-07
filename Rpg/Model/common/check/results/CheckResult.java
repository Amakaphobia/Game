package common.check.results;

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

	@Override
	public boolean isCrit() { return false; }

	@Override
	public boolean isFumble() { return false; }

	/**
	 * This static Method is used to help generating a new LostCheckresult. It calls the {@link #CheckResult(int)}
	 * constructor with the minimum value an int can have.
	 * @return a new {@link CheckResult}
	 */
	public static CheckResult lost() {
		return new CheckResult(Integer.MIN_VALUE);
	}
}
