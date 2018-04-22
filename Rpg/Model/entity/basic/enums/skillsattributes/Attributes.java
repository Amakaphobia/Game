package entity.basic.enums.skillsattributes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to define the possible attributes.
 * @author Dave
 *
 */
public enum Attributes implements Serializable
{
	/**
	 * used for strength <br>
	 * carryweight <br>
     * close combat damage
	 */
	STRENGTH("Strength"),

	/**
	 * used for Agility<br>
	 * walk/ work speed<br>
	 * aiming close combat<br>
	 * Savingthrows
	 */
	DEXTERITY("Dexterity"),


	/**
	 * used for Endurance<br>
	 * Health<br>
	 * Savingthrows
	 */
	CONSTITUTION("Constitution"),

	/**
	 * used for Intelligence<br>
	 * Knowledge<br>
	 * Teaching<br>
	 * Magic Mana
	 */
	INTELLIGENCE("Intelligence"),

	/**
	 * used for Wisdom<br>
	 * streetsmarts<br>
	 * cleric mana
	 */
	WISDOM("Wisdom"),

	/**
	 * Charisma measures a character's personality, personal magnetism, ability to lead, and appearance.
	 * It is the most important ability for paladins, sorcerers, and bards. It is also important for clerics,
	 * since it affects their ability to channel energy. For undead creatures, Charisma is a measure of their unnatural
	 * "lifeforce." Every creature has a Charisma score. A character with a Charisma score of 0 is not able to exert
	 * himself in any way and is unconscious.
	 */
	CHARISMA("Charisma");

	/**holds the name of the Attribute*/
	private String id;
	/**
	 * getter for the Attributes Id
	 * @return a String like Strength
	 */
	public String getId() { return this.id; }

	/**
	 * Constructor
	 * @param id the 'name' of the Attribute
	 */
	private Attributes(String id) {
		this.id = id;
	}

	/**
	 * This method returns all Skills that are effected by this Attribute
	 * @return a {@link List} containing all effected {@link Skills}.
	 */
	public List<Skills> getEffectedSkills(){
		return getEffectedSkills(this);
	}

	/**
	 * This method returns all Skills that are effected by the given Attribute
	 * @param attribute the attribute you want to get the {@link Skills} to.
	 * @return a {@link List} containing all effected {@link Skills}.
	 */
	public static List<Skills> getEffectedSkills(Attributes attribute){
		return Arrays.asList(Skills.values())
					.stream()
					.filter(s -> s.getMainAttribute().equals(attribute))
					.collect(Collectors.toList());
	}
}
