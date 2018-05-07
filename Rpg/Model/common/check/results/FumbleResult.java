package common.check.results;

/**
 * Use this Class when a fumble is landed.
 *
 * @see CheckResult
 *
 * @author Dave
 */
public class FumbleResult extends CheckResult {

	/**Constructor*/
	public FumbleResult() {
		super(Integer.MIN_VALUE);
	}

	@Override
	public boolean isFumble() { return true; }
}
