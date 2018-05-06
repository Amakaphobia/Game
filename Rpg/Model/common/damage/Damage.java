package common.damage;

import dicemachine.I_DiceCode;
import entity.actorBase.ClassedActorBase;
import entity.basic.common.enums.DamageType;

@SuppressWarnings("javadoc")
public class Damage  {

	private boolean rolled = false;

	private final DamageType type;
	private final I_DiceCode die;
	private int rollValue;
	private int actualValue;

	public Damage(I_DiceCode die, DamageType type) {
		super();
		this.type = type;
		this.die = die;
	}

	public int calculateDamage(ClassedActorBase target) {
		if(!rolled) {
			this.rollValue = die.get();
			this.rolled = true;
			double multiplier =
					target.isImmunTo(type) ? 0d :
					target.isResistantTo(type) ? .5 :
					1d;
			this.actualValue = (int) Math.floor(multiplier * rollValue);
		}
		return this.actualValue;
	}
}
