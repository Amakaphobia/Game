package common.check;

/**
 * This interface is implemented by check classes. its only method is one that does a check and returns
 * a {@link I_CheckResult}.
 * @author hdaiv_000
 *
 */
@FunctionalInterface
public interface I_Check 
{
	/**
	 * this method acutally performs the check.
	 * @return the {@link I_CheckResult}  
	 */
	public abstract I_CheckResult doCheck();
}
