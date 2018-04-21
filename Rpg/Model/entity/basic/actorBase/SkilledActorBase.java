package entity.basic.actorBase;

import java.util.Optional;

import javafx.scene.Parent;

import common.check.I_CheckResult;
import common.map.I_GameMap;
import entity.basic.actorBase.container.SkillAttDele;
import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.Attributes;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.Skill;
import entity.basic.skillSet.Skills;
import event.I_Event;

public abstract class SkilledActorBase extends MoveableActorBase implements I_EventTarget{

	/**this container holds the entities skills and attributes*/
	protected final SkillAttDele SkillAndAtt = new SkillAttDele();


	// Constructor

	public SkilledActorBase(
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
	public int getDerivedAttributeModifier(Attributes AttributeId) {
		return this.getAttribute(AttributeId).getDerivedModifier();
	}
	public String getDerivedAttributeModifierAsString(Attributes AttributeId) {
		StringBuilder strb = new StringBuilder();
		int modifier = this.getDerivedAttributeModifier(AttributeId);
		strb.append(modifier >= 0 ? "+ " : "- ");
		if(modifier < 0)
			modifier *= -1;
		strb.append(modifier);
		return strb.toString();
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
