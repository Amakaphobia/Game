package entity.basic.entityBase;

import javafx.scene.Parent;

import common.map.I_GameMap;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.skillSet.I_SkillSet;
import event.I_Event;

public abstract class FightableEntityBase extends SkilledEntityBase implements I_EventTarget {



	/**
	 *
	 * @param name
	 * @param bildPath
	 * @param Map
	 * @param SkillSet
	 * @param AttributeSet
	 */
	public FightableEntityBase(
			String name, String bildPath,
			I_GameMap Map,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet) {

		super(name, bildPath, Map, SkillSet, AttributeSet);
	}

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();

	@Override
	public void handleEvent(I_Event<? super I_EventTarget> event) {
		event.execute(this);
	}

}
