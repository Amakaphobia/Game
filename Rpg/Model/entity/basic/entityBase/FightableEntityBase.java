package entity.basic.entityBase;

import common.map.I_GameMap;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.skillSet.I_SkillSet;
import event.I_Event;
import javafx.scene.Parent;

public abstract class FightableEntityBase extends SkilledEntityBase implements I_EventTarget {

	/**serial*/
	protected static final long serialVersionUID = -4691887039931978132L;

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
