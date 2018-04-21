package entity.basic.entityBase;

import java.util.Optional;

import javafx.scene.Parent;

import common.check.I_CheckResult;
import common.map.I_GameMap;
import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.Attributes;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.entityBase.container.SkillAttDele;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.Skill;
import entity.basic.skillSet.Skills;
import event.I_Event;

@SuppressWarnings("javadoc")
public abstract class SkilledEntityBase extends MoveableEntityBase implements I_EventTarget{

	/**this container holds the entities skills and attributes*/
	protected final SkillAttDele SkillAndAtt = new SkillAttDele();


	// Constructor

	public SkilledEntityBase(
			String name, String bildPath,
			I_GameMap Map,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet) {

		super(name, bildPath, Map);
		this.SkillAndAtt.setSkillSet(SkillSet);
		this.SkillAndAtt.setAttributeSet(AttributeSet);
	}

	@Override
	public void handleEvent(I_Event<? super I_EventTarget> event) {
		event.execute(this);
	}

	//Skill
	public Optional<Skill> getSkill(Skills SkillId) { return this.SkillAndAtt.getSkill(SkillId); }
	public int getSkillLevel(Skills SkillId) { return this.SkillAndAtt.getSkillLevel(SkillId); }
	public I_CheckResult doCheck(final Skills SkillId, final int threshold, final int bonus) {
		return this.SkillAndAtt.doCheck(this, SkillId, threshold, bonus);
	}
	public void trainSkill(Skills SkillId, int level) { this.SkillAndAtt.trainSkill(SkillId, level); }
	public void trainSkill(Skills SkillId) { this.trainSkill(SkillId, 1); }
	public void removeSkill(Skills SkillId) { this.SkillAndAtt.removeSkill(SkillId); }

	//Attribute
	public Attribute getAttribute(Attributes AttributeId) { return this.SkillAndAtt.getAttribute(AttributeId); }
	public int getAttributeLevel(Attributes AttributeId) {
		return this.SkillAndAtt.getAttribute(AttributeId).getValue();
	}
	public void changeAttribute(Attributes AttributeId, int change) {
		this.SkillAndAtt.changeAttribute(AttributeId, change);
	}
	public I_CheckResult doCheck(final Attributes AttributeId, final int threshold, final int bonus) {
		return this.SkillAndAtt.doCheck(this, AttributeId, threshold, bonus);
	}

	//Abstract

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();


}
