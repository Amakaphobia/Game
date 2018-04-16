package entity.basic.attributeSet;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import entity.basic.skillSet.Skills;

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
	 * used for Perception<br>
	 * aiming bow<br>
     * crit chance<br>
     * trap finding<br>
     * Savingthrows
	 */
	PERCEPTION("Perception"),

	/**
	 * used for Agility<br>
	 * walk/ work speed<br>
	 * aiming close combat<br>
	 * Savingthrows
	 */
	AGILITY("Agility"),

	/**
	 * used for Endurance<br>
	 * Health<br>
	 * Savingthrows
	 */
	ENDURANCE("Endurance"),

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
	WISDOM("Wisdom");

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
