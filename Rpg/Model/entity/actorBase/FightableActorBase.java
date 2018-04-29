package entity.actorBase;

import common.map.I_GameMap;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.alignment.Alignment;
import entity.basic.common.enums.clazz.Clazzs;
import entity.basic.race.RaceBase;
import entity.basic.skillSet.I_SkillSet;

@SuppressWarnings("javadoc")
public abstract class FightableActorBase extends ClassedActorBase {


	public FightableActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet,
			Alignment alignment,
			Clazzs clazz) {

		super(name, bildPath, Map, Race, SkillSet, AttributeSet, alignment, clazz);
	}

}
