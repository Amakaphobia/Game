package entity.basic.attributeset;

/**
 * Used to define the possible attributes.
 * @author Dave
 *
 */
public enum Attributes
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
}
