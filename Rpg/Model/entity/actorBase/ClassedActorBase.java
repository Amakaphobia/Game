package entity.actorBase;

import common.map.I_GameMap;
import entity.actorBase.container.HealthPointContainer;
import entity.actorBase.container.I_HasHp;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.alignment.Alignment;
import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.race.RaceBase;
import entity.basic.skillSet.I_SkillSet;
import entity.clazz.ClazzBase;

@SuppressWarnings("javadoc")
public abstract class ClassedActorBase extends SkilledActorBase implements I_HasHp{

	/**this container holds the entities Hp*/
	protected final HealthPointContainer Hp;

	public ClassedActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet,
			Alignment Alignment) {
		super(name, bildPath, Map, Race, SkillSet, AttributeSet, Alignment);

		this.Hp = new HealthPointContainer(this);
	}

	public void onClassLevelUp(ClazzBase clazz) {
		this.onClassLevelUpHitDie(clazz);
	}

	protected void onClassLevelUpHitDie(ClazzBase Clazz) {
		StringBuilder strb = new StringBuilder(Clazz.getHitDieCode());
		strb.append(this.getDerivedAttributeModifierAsString(Attributes.CONSTITUTION));
		if(Clazz.getLevel() == 1)
			this.Hp.addHitDieMax(strb.toString());
		else
			this.Hp.addHitDie(strb.toString());
	}

	/**
	 * {@inheritDoc}<br>
	 * It will set {@link #setLiving(boolean)} to false.
	 */
	@Override
	public void onHealthZero() {
		this.setLiving(false);
	}

	@Override
	public void onHealthNoLongerZero() {
		this.setLiving(true);

	}

}
