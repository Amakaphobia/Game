package common.damage;

import dicemachine.I_DiceCode;
import entity.basic.common.enums.DamageType;

/**
 * This Class represents a damage object. It has a Die and a type.
 *
 * @author Dave
 *
 * @see DamageType
 * @see I_DiceCode
 */
public class Damage  {

	/**the damage type*/
	private final DamageType type;
	/**@return {@link #type}*/
	public final DamageType getType() { return this.type; }
	/**the die used to calculate the damage*/
	private final I_DiceCode die;
	/**@return {@link #die}*/
	public final I_DiceCode getDie() { return this.die; }

	/**
	 * Constructor
	 * @param die the damage die
	 * @param type the type of damage
	 */
	public Damage(I_DiceCode die, DamageType type) {
		super();
		this.type = type;
		this.die = die;
	}

	/**
	 * this method is used to get the damage value of this object
	 * @return a int containing damage points
	 */
	public int getDamage() {
		return this.die.getValue();
	}
}
