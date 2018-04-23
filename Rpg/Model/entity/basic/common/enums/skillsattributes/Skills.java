package entity.basic.common.enums.skillsattributes;

import java.io.Serializable;

/**
 * This Enum represents all learnable skills. It is used as Key.
 * @author Dave
 *
 */
public enum Skills implements Serializable{
	/**
	 * An empty Skill Dummy
	 */
	EMPTY("", null, false),

	/**
	 * Acrobatic is used to represent things like abseiling, jumping, general Acrobatic stuff
	 */
	ACROBATIC("Acrobatic", Attributes.DEXTERITY, true),

	APPRAISE("Appraise", Attributes.INTELLIGENCE, true),
	BLUFF("Bluff", Attributes.CHARISMA, true),
	CLIMB("Climb", Attributes.STRENGTH, true),
	CRAFT("Craft", Attributes.INTELLIGENCE, true),
	DIPLOMACY("Diplomacy", Attributes.CHARISMA, true),
	DISABLE_DEVICE("Disable Device", Attributes.DEXTERITY, false),
	DISGUISE("Disguise", Attributes.CHARISMA, true),
	ESCAPE_ARTIST("Escape Artist", Attributes.DEXTERITY, true),
	FLY("Fly", Attributes.DEXTERITY, true),
	HANDLE_ANIMAL("Handle Animal", Attributes.CHARISMA, false),
	HEAL("Heal", Attributes.WISDOM, true),
	INTIMIDATE("Intimidate", Attributes.CHARISMA, true),
	KNOWLEDGE_ARCANA("Knowledge: Arcana", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_DUNGEONEERING("Knowledge: Dungeoneering", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_ENGINEERING("Knowledge: Engineering", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_GEOGRAPHY("Knowledge: Geography", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_HISTORY("Knowledge: History", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_LOCAL("Knowledge: Local", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_NATURE("Knowledge: Nature", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_NOBILITY("Knowledge: Nobility", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_PLANES("Knowledge: Planes", Attributes.INTELLIGENCE, false),
	KNOWLEDGE_RELIGION("Knowledge: Religion", Attributes.INTELLIGENCE, false),
	LINGUISTISCS("Linguistics", Attributes.INTELLIGENCE, false),
	PERCEPTION("Perception", Attributes.WISDOM, true),
	PERFORM("Perform", Attributes.CHARISMA, true),
	PROFESSION_FIRST("Profession", Attributes.WISDOM, false),
	PROFESSION_SECOND("Profession", Attributes.WISDOM, false),
	PROFESSION_THIRD("Profession", Attributes.WISDOM, false),
	RIDE("Ride", Attributes.DEXTERITY, true),
	SENSE_MOTIVE("Sense Motive", Attributes.WISDOM, true),
	SLEIGHT_OF_HAND("Sleight of Hand", Attributes.DEXTERITY, false),
	SPELLCRAFT("Spellcraft", Attributes.INTELLIGENCE, false),
	STEALTH("Stealth", Attributes.DEXTERITY, true),
	SURVIVAL("Survival", Attributes.WISDOM, true),

	/**
	 * This is used to move in water.
	 */
	SWIM("Swim", Attributes.STRENGTH, true),

	USE_MAGIC_DEVICE("Use magic device", Attributes.CHARISMA, false);

	/**
	 * Constructor
	 * @param id the Name of the Skill properly capitalized
	 * @param mainAttribute the main {@link Attributes} for this skill
	 * @param untrained set to true if this skill can be attempted untrained.
	 */
	private Skills(String id, Attributes mainAttribute, boolean untrained) {
		this.id = id;
		this.mainAttribute = mainAttribute;
		this.untrained = untrained;
	}

	/**holds the Skills name*/
	private String id;
	/**the {@link Attributes} that effects this skill*/
	private Attributes mainAttribute;
	/**is true if the skill can be used if you're not trained*/
	private boolean untrained;
	/**
	 * used to access the {@link Skills#untrained} value.
	 * @return true if this skill can be attempted untrained.
	 */
	public boolean isUntrained() { return this.untrained; }

	/**
	 * return the Skills name
	 * @return a String containing the name
	 */
	public String getId() { return this.id; }

	/**
	 * return the main {@link Attributes} for this skill
	 * @return the {@link Attributes} that effects this skill
	 */
	public Attributes getMainAttribute() { return this.mainAttribute; }


}
