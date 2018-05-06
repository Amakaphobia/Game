package common.damage;

import dicemachine.I_DiceCode;
import entity.basic.common.enums.DamageType;

@SuppressWarnings("javadoc") // TODO docu
public class Damage  {

	private boolean rolled = false;

	private final DamageType type;
	public final DamageType getType() { return this.type; }
	private final I_DiceCode die;
	private int rollValue;
	private boolean crit;

	public Damage(I_DiceCode die, DamageType type, boolean crit) {
		super();
		this.type = type;
		this.die = die;
		this.crit = crit;
	}

	public int getDamage() {
		if(!rolled) {
			this.rollValue = die.get();
			this.rolled = true;
			/*
			double multiplier =
					target.isImmunTo(type) ? 0d :
					target.isResistantTo(type) ? .5 :
					1d;
			this.actualValue = (int) Math.floor(multiplier * rollValue);*/
		}
		return this.rollValue;
	}
}
