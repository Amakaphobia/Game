package entity.actorBase;

import javafx.scene.Parent;

import common.map.I_GameMap;
import entity.basic.alignment.Alignment;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.skillSet.I_SkillSet;

@SuppressWarnings("javadoc")
public abstract class FightableActorBase extends ClassedActorBase {


	public FightableActorBase(
			String name, String bildPath,
			I_GameMap Map,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet, Alignment alignment) {

		super(name, bildPath, Map, SkillSet, AttributeSet, alignment);
	}

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();

}
