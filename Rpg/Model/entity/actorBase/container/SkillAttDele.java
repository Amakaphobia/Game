package entity.actorBase.container;

import java.io.Serializable;
import java.util.Optional;

import common.check.checks.AttributeCheck;
import common.check.checks.I_Check;
import common.check.checks.SkillCheck;
import common.check.results.I_CheckResult;
import entity.actorBase.SkilledActorBase;
import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.AttributeSet;
import entity.basic.attributeSet.I_Attribute;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.common.enums.skillsattributes.Skills;
import entity.basic.skillSet.I_Skill;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.SkillSet;

/**
 * This is a simple container class for a skillset and a attribute set to encapsulate logic out of the actor classes
 *
 * @author Dave
 * @see I_AttributeSet
 * @see I_SkillSet
 * @see SkilledActorBase
 */
public class SkillAttDele implements Serializable {

	//Objs
	/**the SkillSet*/
	private I_SkillSet SkillSet;
	/**
	 * This Method is used to set the Skillset
	 * @param SkillSet the new Skillset
	 */
	public void setSkillSet(I_SkillSet SkillSet) { this.SkillSet = SkillSet; }
	/**
	 * this method is used to access the skillset
	 * @return the skillset of this object
	 */
	public I_SkillSet getSkillSet() { return this.SkillSet; }

	/**the attribute table*/
	private I_AttributeSet AttributeSet;
	/**
	 * This Method is used to set the AttributeSet
	 * @param AttributeSet the new AttributeSet
	 */
	public void setAttributeSet(I_AttributeSet AttributeSet) { this.AttributeSet = AttributeSet; }
	/**
	 * this method is used to access the AttributeSet
	 * @return the AttributeSet of this object
	 */
	public I_AttributeSet getAttributeSet() { return this.AttributeSet; }

	/**The Owner of the container*/
	@SuppressWarnings("unused")
	private SkilledActorBase Parent;

	//Constructors

	// TODO implement SS and AS factories
	/**
	 * Constructor will use empty skills and attributes
	 * @param Parent the owner of this container
	 */
	public SkillAttDele(SkilledActorBase Parent) {
		this(Parent, new SkillSet(), new AttributeSet());
	}

	/**
	 * Constructor
	 * @param Parent the owner of this container
	 * @param SkillSet the skillset you want to initiate
	 * @param AttributeSet the attributetable you want to initiate
	 */
	public SkillAttDele(SkilledActorBase Parent, I_SkillSet SkillSet, I_AttributeSet AttributeSet) {
		super();
		this.Parent = Parent;
		this.SkillSet = SkillSet;
		this.AttributeSet = AttributeSet;
	}

	//SkillSet methods
	/**
	 * @see I_SkillSet#getSkill(Skills)
	 * @param SkillId a {@link Skills} enum that defines what skill is searched
	 * @return a optional containing the found skill or empty if the skill is not part of this set.
	 */
	public Optional<I_Skill> getSkill(Skills SkillId) { return this.SkillSet.getSkill(SkillId); }
	/**
	 * @see I_SkillSet#getSkillLevel(Skills)
	 * @param SkillId the Skill you are looking for
	 * @return the level of the skill as an Integer. 0 if it is not known.
	 */
	public int getSkillLevel(Skills SkillId) { return this.SkillSet.getSkillLevel(SkillId); }
	/**
	 * @see I_SkillSet#trainSkill(Skills, int)
	 * @param SkillId the {@link Skills} you want to train
	 * @param level how many levels (positive and negative) you want to train
	 */
	public void trainSkill(Skills SkillId, int level) {
		if(this.SkillSet.hasSkill(SkillId))
			this.SkillSet.trainSkill(SkillId, level);
		else if(level > 0)
			this.SkillSet.addSkill(SkillId, level);
	}
	/**
	 * @see I_SkillSet#removeSkill(Skills)
	 * @param SkillId the {@link Skills} enum that defines the skill you want to remove
	 */
	public void removeSkill(Skills SkillId) { this.SkillSet.removeSkill(SkillId); }
	/**
	 * @see SkillCheck#doCheck()
	 * @param Actor the Actor that is performing the check
	 * @param SkillId the id of the skill you want to check
	 * @param threshold the dc of this skillCheck
	 * @param bonus the bonus depending on circumstances for the check
	 * @return the {@link I_CheckResult} for this check
	 */
	public I_CheckResult doCheck(
			final SkilledActorBase Actor,
			final Skills SkillId,
			final int threshold,
			final int bonus) {
		I_Check check = new SkillCheck(Actor, SkillId, threshold, bonus);

		return check.doCheck();
	}

	//AttributeMethods
	/**
	 * @see I_AttributeSet#getAttribute(Attributes)
	 * @param AttributeId  {@link Attributes} value
	 * @return the searched {@link Attribute}.
	 */
	public I_Attribute getAttribute(Attributes AttributeId) {
		return this.AttributeSet.getAttribute(AttributeId);
	}
	/**
	 * This Method is used to get the Level of a given {@link Attributes}
	 * @param AttributeId the Attribute searched for
	 * @return integer representing the level of the attribute
	 */
	public int getAttributeLevel(Attributes AttributeId) {
		return this.AttributeSet.getAttribute(AttributeId).getValue();
	}
	/**
	 * this method is used to add to/subtract from a given attribute a given change
	 * @param AttributeId the attribute searched for
	 * @param change the change positive for gain and negative for loss
	 */
	public void changeAttribute(Attributes AttributeId, int change) {
		final I_Attribute ToChange = this.getAttribute(AttributeId);
		final int newLevel = ToChange.getValue() + change;

		ToChange.setValue(newLevel >= 0 ? newLevel : 0);
	}
	/**
	 * @see AttributeCheck#doCheck()
	 * @param Actor the Actor that is performing the check
	 * @param AttributeId the id of the Attribute you want to check
	 * @param threshold the dc of this AttributeCheck
	 * @param bonus the bonus depending on circumstances for the check
	 * @return the {@link I_CheckResult} for this check
	 */
	public I_CheckResult doCheck(
			final SkilledActorBase Actor,
			final Attributes AttributeId,
			final int threshold,
			final int bonus) {
		I_Check check = new AttributeCheck(Actor, AttributeId, threshold, bonus);

		return check.doCheck();
	}
	/**
	 * This method gets the derived modifier of a given attribute as a string in form of "+/- modifer"
	 * @param AttributeId the {@link Attributes} searched for
	 * @return the derivedModifier string
	 */
	public String getDerivedAttributeModifierAsString(Attributes AttributeId) {
		return this.AttributeSet.getDerivedAttributeModifierAsString(AttributeId);
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
