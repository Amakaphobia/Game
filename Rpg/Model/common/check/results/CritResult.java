package common.check.results;

/**
 * Use this Class when a crit is landed.
 *
 * @see CheckResult
 *
 * @author Dave
 */
public class CritResult extends CheckResult {

	/**Constructor*/
	public CritResult() {
		super(Integer.MAX_VALUE);
	}

	@Override
	public boolean isCrit() { return true; }
}
