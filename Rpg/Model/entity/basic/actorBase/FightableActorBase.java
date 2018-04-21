package entity.basic.actorBase;

import javafx.scene.Parent;

import common.map.I_GameMap;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.skillSet.I_SkillSet;

@SuppressWarnings("javadoc")
public abstract class FightableActorBase extends ClassedActorBase {


	public FightableActorBase(
			String name, String bildPath,
			I_GameMap Map,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet) {

		super(name, bildPath, Map, SkillSet, AttributeSet);
	}

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();

}
