package common.attack;

/**
 * Simple Implementation of the {@link I_Attack} interface. Bareley more than an int.
 * @author Dave
 *
 */
public class SimpleAttack implements I_Attack{

	/**
	 * Constructor
	 * @param baseAttackBonus the baseAttackBonus of this Attack
	 */
	public SimpleAttack(int baseAttackBonus) {
		super();
		this.baseAttackBonus = baseAttackBonus;
	}

	/**holds the baseAttackBonus*/
	private final int baseAttackBonus;

	@Override
	public int getBaseAttackBonus() { return this.baseAttackBonus; }
}
