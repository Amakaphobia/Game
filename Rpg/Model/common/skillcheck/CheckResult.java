package common.skillcheck;

public class CheckResult implements I_CheckResult {

	private int resultDiff;
	private boolean passed;
	
	public CheckResult(int resultDiff) {
		this.resultDiff = resultDiff;
		this.passed = resultDiff >= 0;
	}

	@Override
	public int getResultDifference() { return this.resultDiff; }

	@Override
	public boolean isPassed() { return this.passed; }

}
