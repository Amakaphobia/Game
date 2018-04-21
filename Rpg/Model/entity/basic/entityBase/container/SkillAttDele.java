package entity.basic.entityBase.container;

import java.util.Optional;

import common.check.AttributeCheck;
import common.check.I_Check;
import common.check.I_CheckResult;
import common.check.SkillCheck;
import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.Attributes;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.entityBase.SkilledEntityBase;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.Skill;
import entity.basic.skillSet.Skills;

@SuppressWarnings("javadoc")
public class SkillAttDele {

	//Objs

	private I_SkillSet SkillSet;
	public void setSkillSet(I_SkillSet SkillSet) { this.SkillSet = SkillSet; }
	public I_SkillSet getSkillSet() { return this.SkillSet; }

	private I_AttributeSet AttributeSet;
	public void setAttributeSet(I_AttributeSet AttributeSet) { this.AttributeSet = AttributeSet; }
	public I_AttributeSet getAttributeSet() { return this.AttributeSet; }

	//Const

	public SkillAttDele() {
		super();
	}

	public SkillAttDele(I_SkillSet SkillSet, I_AttributeSet AttributeSet) {
		this();
		this.SkillSet = SkillSet;
		this.AttributeSet = AttributeSet;
	}

	//SkillSet methods

	public Optional<Skill> getSkill(Skills SkillId) { return this.SkillSet.getSkill(SkillId); }
	public int getSkillLevel(Skills SkillId) { return this.SkillSet.getSkillLevel(SkillId); }

	public void trainSkill(Skills SkillId, int level) {
		if(this.SkillSet.hasSkill(SkillId))
			this.SkillSet.trainSkill(SkillId, level);
		else if(level > 0)
			this.SkillSet.addSkill(SkillId, level);
	}
	public void removeSkill(Skills SkillId) { this.SkillSet.removeSkill(SkillId); }

	public I_CheckResult doCheck(
			final SkilledEntityBase Actor,
			final Skills SkillId,
			final int threshold,
			final int bonus) {
		I_Check check = new SkillCheck(Actor, SkillId, threshold, bonus);

		return check.docheck();
	}

	//AttributeMethods

	public Attribute getAttribute(Attributes AttributeId) {
		return this.AttributeSet.getAttribute(AttributeId);
	}
	public int getAttributeLevel(Attributes AttributeId) {
		return this.AttributeSet.getAttribute(AttributeId).getValue();
	}

	public void changeAttribute(Attributes AttributeId, int change) {
		final Attribute ToChange = this.getAttribute(AttributeId);
		final int newLevel = ToChange.getValue() + change;

		ToChange.setValue(newLevel >= 0 ? newLevel : 0);
	}

	public I_CheckResult doCheck(
			final SkilledEntityBase Actor,
			final Attributes AttributeId,
			final int threshold,
			final int bonus) {
		I_Check check = new AttributeCheck(Actor, AttributeId, threshold, bonus);

		return check.docheck();
	}

	//ObjMethods

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof SkillAttDele)) return false;

		SkillAttDele other = (SkillAttDele) obj;

		return this.SkillSet.equals(other.SkillSet)
			&&  this.AttributeSet.equals(other.AttributeSet);
	}

	@Override
	public String toString() { return String.format("%s\n%s", this.AttributeSet, this.SkillSet); }

	@Override
	public int hashCode() {
		int SkillSetCode = this.SkillSet.hashCode();
		int AttSetCode = this.AttributeSet.hashCode();

		return SkillSetCode + AttSetCode * AttSetCode;
	}
}
