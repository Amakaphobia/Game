package entity.basic.entityBase;

import java.util.Optional;

import javafx.scene.Parent;

import common.map.I_GameMap;
import common.skillcheck.AttributeCheck;
import common.skillcheck.I_Check;
import common.skillcheck.I_CheckResult;
import common.skillcheck.SkillCheck;
import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.Attributes;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.Skill;
import entity.basic.skillSet.Skills;
import event.I_Event;

@SuppressWarnings("javadoc")
public abstract class SkilledEntityBase extends MoveableEntityBase implements I_EventTarget{
	/**serial*/
	private static final long serialVersionUID = -6626140214455088421L;

	// Skills Delegate

	protected I_SkillSet SkillSet;
	public I_SkillSet getSkillSet() { return this.SkillSet; }
	public Optional<Skill> getSkill(Skills SkillId) { return this.SkillSet.getSkill(SkillId); }
	public int getSkillLevel(Skills SkillId) { return this.SkillSet.getSkillLevel(SkillId); }

	public void trainSkill(Skills SkillId, int level) {
		if(this.SkillSet.hasSkill(SkillId))
			this.SkillSet.trainSkill(SkillId, level);
		else if(level > 0)
			this.SkillSet.addSkill(SkillId, level);
	}
	public void trainSkill(Skills SkillId) { this.trainSkill(SkillId, 1); }
	public void removeSkill(Skills SkillId) { this.SkillSet.removeSkill(SkillId); }

	public I_CheckResult doSkillCheck(final Skills SkillId, final int threshold, final int bonus) {
		I_Check check = new SkillCheck(this, SkillId, threshold, bonus);

		return check.docheck();
	}

	// Attributes Delegate

	protected I_AttributeSet AttributeSet;
	public I_AttributeSet getAttributeSet() { return this.AttributeSet; }
	public Attribute getAttribute(Attributes AttributeId) { return this.AttributeSet.getAttribute(AttributeId); }
	public int getAttributeLevel(Attributes AttributeId) { return this.AttributeSet.getAttribute(AttributeId).getValue(); }

	public void changeAttribute(Attributes AttributeId, int change) {
		final Attribute ToChange = this.getAttribute(AttributeId);
		final int newLevel = ToChange.getValue() + change;

		ToChange.setValue(newLevel >= 0 ? newLevel : 0);
	}

	public I_CheckResult doAttributeCheck(final Attributes AttributeId, final int threshold, final int bonus) {
		I_Check check = new AttributeCheck(this, AttributeId, threshold, bonus);

		return check.docheck();
	}

	// Constructor

	public SkilledEntityBase(
			String name, String bildPath,
			I_GameMap Map,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet) {
		super(name, bildPath, Map);
		this.SkillSet = SkillSet;
		this.AttributeSet = AttributeSet;
	}

	public void updateMaxHealth(String hitDieCode) {
		// TODO onClassLevelUp
	}

	@Override
	public void handleEvent(I_Event<? super I_EventTarget> event) {
		event.execute(this);
	}

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();

}
