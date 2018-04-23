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
	 * You can keep your balance while traversing narrow or treacherous surfaces.
	 * You can also dive, flip, jump, and roll to avoid attacks and overcome obstacles.
	 */
	ACROBATIC("Acrobatic", Attributes.DEXTERITY, true),

	/**
	 * You can evaluate the monetary value of an object.
	 */
	APPRAISE("Appraise", Attributes.INTELLIGENCE, true),

	/**
	 * You know how to tell a lie.
	 */
	BLUFF("Bluff", Attributes.CHARISMA, true),

	/**
	 * You are skilled at scaling vertical surfaces, from smooth city walls to rocky cliffs.
	 */
	CLIMB("Climb", Attributes.STRENGTH, true),

	/**
	 * You are skilled in the creation of a specific group of items, such as armor or weapons. Like Knowledge, Perform,
	 * and Profession, Craft is actually a number of separate skills. You could have several Craft skills, each with its
	 * own ranks.
	 * The most common Craft skills are alchemy, armor, baskets, books, bows, calligraphy, carpentry, cloth, clothing,
	 *  glass, jewelry, leather, locks, paintings, pottery, sculptures, ships, shoes, stonemasonry, traps, and weapons.
	 * A Craft skill is specifically focused on creating something. If nothing is created by the endeavor, it probably
	 * falls under the heading of a Profession skill.
	 */
	CRAFT("Craft", Attributes.INTELLIGENCE, true),

	/**
	 * You can use this skill to persuade others to agree with your arguments, to resolve differences, and to gather
	 * valuable information or rumors from people. This skill is also used to negotiate conflicts by using the proper
	 * etiquette and manners suitable to the problem.
	 */
	DIPLOMACY("Diplomacy", Attributes.CHARISMA, true),

	/**
	 * You are skilled at disarming traps and opening locks. In addition, this skill lets you sabotage simple mechanical
	 * devices, such as catapults, wagon wheels, and doors.
	 */
	DISABLE_DEVICE("Disable Device", Attributes.DEXTERITY, false),

	/**
	 * You are skilled at changing your appearance.
	 */
	DISGUISE("Disguise", Attributes.CHARISMA, true),

	/**
	 * Your training allows you to slip out of bonds and escape from grapples.
	 */
	ESCAPE_ARTIST("Escape Artist", Attributes.DEXTERITY, true),

	/**
	 * You are skilled at flying, through either the use of wings or magic, and can perform daring or complex
	 * maneuvers while airborne. Note that this skill does not give you the ability to fly.
	 */
	FLY("Fly", Attributes.DEXTERITY, true),

	/**
	 * You are trained at working with animals, and can teach them tricks, get them to follow your simple commands,
	 * or even domesticate them.
	 */
	HANDLE_ANIMAL("Handle Animal", Attributes.CHARISMA, false),

	/**
	 * You are skilled at tending to wounds and ailments.
	 */
	HEAL("Heal", Attributes.WISDOM, true),

	/**
	 * You can use this skill to frighten an opponent or to get them to act in a way that benefits you. This skill
	 * includes verbal threats and displays of prowess.
	 */
	INTIMIDATE("Intimidate", Attributes.CHARISMA, true),

	/**
	 * ancient mysteries, magic traditions, arcane symbols, constructs, dragons, magical beasts
	 */
	KNOWLEDGE_ARCANA("Knowledge: Arcana", Attributes.INTELLIGENCE, false),

	/**
	 * aberrations, caverns, oozes, spelunking
	 */
	KNOWLEDGE_DUNGEONEERING("Knowledge: Dungeoneering", Attributes.INTELLIGENCE, false),

	/**
	 * buildings, aqueducts, bridges, fortifications
	 */
	KNOWLEDGE_ENGINEERING("Knowledge: Engineering", Attributes.INTELLIGENCE, false),

	/**
	 * lands, terrain, climate, people
	 */
	KNOWLEDGE_GEOGRAPHY("Knowledge: Geography", Attributes.INTELLIGENCE, false),

	/**
	 * wars, colonies, migrations, founding of cities
	 */
	KNOWLEDGE_HISTORY("Knowledge: History", Attributes.INTELLIGENCE, false),

	/**
	 * legends, personalities, inhabitants, laws, customs, traditions, humanoids
	 */
	KNOWLEDGE_LOCAL("Knowledge: Local", Attributes.INTELLIGENCE, false),

	/**
	 * animals, fey, monstrous humanoids, plants, seasons and cycles, weather, vermin
	 */
	KNOWLEDGE_NATURE("Knowledge: Nature", Attributes.INTELLIGENCE, false),

	/**
	 * lineages, heraldry, personalities, royalty
	 */
	KNOWLEDGE_NOBILITY("Knowledge: Nobility", Attributes.INTELLIGENCE, false),

	/**
	 * the Inner Planes, the Outer Planes, the Astral Plane, the Ethereal Plane, outsiders, planar magic
	 */
	KNOWLEDGE_PLANES("Knowledge: Planes", Attributes.INTELLIGENCE, false),

	/**
	 * gods and goddesses, mythic history, ecclesiastic tradition, holy symbols, undead
	 */
	KNOWLEDGE_RELIGION("Knowledge: Religion", Attributes.INTELLIGENCE, false),

	/**
	 * You are skilled at working with language, in both its spoken and written forms. You can speak multiple
	 * languages, and can decipher nearly any tongue given enough time. Your skill in writing allows you to create
	 * and detect forgeries as well.
	 */
	LINGUISTISCS("Linguistics", Attributes.INTELLIGENCE, false),

	/**
	 * Your senses allow you to notice fine details and alert you to danger. Perception covers all five senses,
	 * including sight, hearing, touch, taste, and smell.
	 */
	PERCEPTION("Perception", Attributes.WISDOM, true),

	/**
	 * You are skilled at one form of entertainment, from singing to acting to playing an instrument. Like Craft,
	 * Knowledge, and Profession, Perform is actually a number of separate skills. You could have several Perform skills,
	 * each with its own ranks.
	 * Each of the nine categories of the Perform skill includes a variety of methods, instruments, or techniques,
	 * a small sample of which is provided for each category below.
	 */
	PERFORM("Perform", Attributes.CHARISMA, true),

	/**
	 * You are skilled at a specific job. Like Craft, Knowledge, and Perform, Profession is actually a number of
	 * separate skills. You could have several Profession skills, each with its own ranks. While a Craft skill
	 * represents ability in creating an item, a Profession skill represents an aptitude in a vocation requiring a
	 * broader range of less specific knowledge. The most common Profession skills are architect, baker, barrister,
	 * brewer, butcher, clerk, cook, courtesan, driver, engineer, farmer, fisherman, gambler, gardener, herbalist,
	 * innkeeper, librarian, merchant, midwife, miller, miner, porter, sailor, scribe, shepherd, stable master, soldier,
	 * tanner, trapper, and woodcutter.
	 */
	PROFESSION("Profession", Attributes.WISDOM, false),

	/**
	 * You are skilled at riding mounts, usually a horse, but possibly something more exotic, like a griffon or pegasus.
	 * If you attempt to ride a creature that is ill suited as a mount, you take a –5 penalty on your Ride checks.
	 */
	RIDE("Ride", Attributes.DEXTERITY, true),

	/**
	 * You are skilled at detecting falsehoods and true intentions.
	 */
	SENSE_MOTIVE("Sense Motive", Attributes.WISDOM, true),

	/**
	 * Your training allows you to pick pockets, draw hidden weapons, and take a variety of actions without being
	 * noticed.
	 */
	SLEIGHT_OF_HAND("Sleight of Hand", Attributes.DEXTERITY, false),

	/**
	 * You are skilled at the art of casting spells, identifying magic items, crafting magic items, and identifying
	 * spells as they are being cast.
	 */
	SPELLCRAFT("Spellcraft", Attributes.INTELLIGENCE, false),

	/**
	 * You are skilled at avoiding detection, allowing you to slip past foes or strike from an unseen position.
	 * This skill covers hiding and moving silently.
	 */
	STEALTH("Stealth", Attributes.DEXTERITY, true),

	/**
	 * You are skilled at surviving in the wild and at navigating in the wilderness.
	 * You also excel at following trails and tracks left by others.
	 */
	SURVIVAL("Survival", Attributes.WISDOM, true),

	/**
	 * You know how to swim and can do so even in stormy water.
	 */
	SWIM("Swim", Attributes.STRENGTH, true),

	/**
	 * You are skilled at activating magic items, even if you are not otherwise trained in their use.
	 */
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
