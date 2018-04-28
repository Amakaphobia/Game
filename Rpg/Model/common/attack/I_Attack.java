package common.attack;

import java.io.Serializable;

/**
 * Interface implemented by combat Attacks
 * @author Dave
 *
 */
public interface I_Attack extends Serializable{
	/**
	 * Use this method to access the BaseAttackBonus of this attack
	 * @return integer : the base Attack Bonus
	 */
	public abstract int getBaseAttackBonus();

}
