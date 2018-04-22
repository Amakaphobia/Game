package entity.actorBase;

import java.util.Optional;

import javafx.scene.Parent;

import common.check.AttributeCheck;
import common.check.I_CheckResult;
import common.check.SkillCheck;
import common.map.I_GameMap;
import entity.actorBase.container.SkillAttDele;
import entity.basic.EntityBase;
import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.enums.alignment.Alignment;
import entity.basic.enums.skillsattributes.Attributes;
import entity.basic.enums.skillsattributes.Skills;
import entity.basic.race.RaceBase;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.Skill;
import event.I_Event;
import event.I_EventTarget;

/**
 * This is the BaseImplementation for all Actors that have skills and attributes.
 *
 * @see MoveableActorBase
 * @see EntityBase
 * @see I_EventTarget
 * @author Dave
 *
 */
public abstract class SkilledActorBase extends RacialActorBase implements I_EventTarget{

	/**this container holds the entities skills and attributes*/
	protected final SkillAttDele SkillAndAtt;

	/**holds the alignment of this Actor*/
	private Alignment Alignment;
	/**@return the alignment*/
	public Alignment getAlignment() { return this.Alignment; }
	/**@param alignment the alignment to set*/
	public void setAlignment(Alignment alignment) { this.Alignment = alignment; }

	/**
	 * Constructor
	 * @param name the name of this Actor
	 * @param bildPath the path to the picture of this entity
	 * @param Map the gamemap
	 * @param Race The Race of the Actor
	 * @param SkillSet the skillset of this actor
	 * @param AttributeSet the attribute table of this actor
	 * @param Alignment the alignment of this actor
	 */
	public SkilledActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet,
			Alignment Alignment) {

		super(name, bildPath, Map, Race);
		this.SkillAndAtt = new SkillAttDele(this,SkillSet, AttributeSet);
		this.Alignment = Alignment;
	}

	/**{@inheritDoc}*/
	@Override
	public void handleEvent(I_Event<? super I_EventTarget> event) {
		event.execute(this);
	}

	//Skill
	/**
	 * @see I_SkillSet#getSkill(Skills)
	 * @param SkillId a {@link Skills} enum that defines what skill is searched
	 * @return a optional containing the found skill or empty if the skill is not part of this set.
	 */
	public Optional<Skill> getSkill(Skills SkillId) { return this.SkillAndAtt.getSkill(SkillId); }
	/**
	 * @see I_SkillSet#getSkillLevel(Skills)
	 * @param SkillId the Skill you are looking for
	 * @return the level of the skill as an Integer. 0 if it is not known.
	 */
	public int getSkillLevel(Skills SkillId) { return this.SkillAndAtt.getSkillLevel(SkillId); }
	/**
	 * @see SkillCheck#doCheck()
	 * @param SkillId the id of the skill you want to check
	 * @param threshold the dc of this skillCheck
	 * @param bonus the bonus depending on circumstances for the check
	 * @return the {@link I_CheckResult} for this check
	 */
	public I_CheckResult doCheck(final Skills SkillId, final int threshold, final int bonus) {
		return this.SkillAndAtt.doCheck(this, SkillId, threshold, bonus);
	}
	/**
	 * @see I_SkillSet#trainSkill(Skills, int)
	 * @param SkillId the {@link Skills} you want to train
	 * @param level how many levels (positive and negative) you want to train
	 */
	public void trainSkill(Skills SkillId, int level) { this.SkillAndAtt.trainSkill(SkillId, level); }
	/**
	 * short for to call {@link #trainSkill(Skills, int)} with 1 as the second argument
	 * @param SkillId the {@link Skills} you want to train
	 */
	public void trainSkill(Skills SkillId) { this.trainSkill(SkillId, 1); }
	/**
	 * @see I_SkillSet#removeSkill(Skills)
	 * @param SkillId the {@link Skills} enum that defines the skill you want to remove
	 */
	public void removeSkill(Skills SkillId) { this.SkillAndAtt.removeSkill(SkillId); }

	//Attribute
	/**
	 * @see I_AttributeSet#getAttribute(Attributes)
	 * @param AttributeId  {@link Attributes} value
	 * @return the searched {@link Attribute}.
	 */
	public Attribute getAttribute(Attributes AttributeId) { return this.SkillAndAtt.getAttribute(AttributeId); }
	/**
	 * This Method is used to get the Level of a given {@link Attributes}
	 * @param AttributeId the Attribute searched for
	 * @return integer representing the level of the attribute
	 */
	public int getAttributeLevel(Attributes AttributeId) {
		return this.SkillAndAtt.getAttribute(AttributeId).getValue();
	}
	/**
	 * This method is used to access the derived modifier for a given {@link Attributes}
	 * @see Attribute#derivedModifierProperty()
	 * @param AttributeId the Attribute searched for
	 * @return the derived modifier
	 */
	public int getDerivedAttributeModifier(Attributes AttributeId) {
		return this.getAttribute(AttributeId).getDerivedModifier();
	}
	/**
	 * This method gets the derived modifier of a given attribute as a string in form of "+/- modifer"
	 * @see #getDerivedAttributeModifier(Attributes)
	 * @param AttributeId the {@link Attributes} searched for
	 * @return the derivedModifier string
	 */
	public String getDerivedAttributeModifierAsString(Attributes AttributeId) {
		return this.SkillAndAtt.getDerivedAttributeModifierAsString(AttributeId);
	}
	/**
	 * this method is used to add to/subtract from a given attribute a given change
	 * @param AttributeId the attribute searched for
	 * @param change the change positive for gain and negative for loss
	 */
	public void changeAttribute(Attributes AttributeId, int change) {
		this.SkillAndAtt.changeAttribute(AttributeId, change);
	}
	/**
	 * @see AttributeCheck#doCheck()
	 * @param AttributeId the id of the Attribute you want to check
	 * @param threshold the dc of this AttributeCheck
	 * @param bonus the bonus depending on circumstances for the check
	 * @return the {@link I_CheckResult} for this check
	 */
	public I_CheckResult doCheck(final Attributes AttributeId, final int threshold, final int bonus) {
		return this.SkillAndAtt.doCheck(this, AttributeId, threshold, bonus);
	}

	//Abstract

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();




}
