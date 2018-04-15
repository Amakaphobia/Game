package entity.basic.attributeset;

/**
 * Used to define the possible attributes.
 * @author Dave
 *
 */
public enum Attributes
{
	/**used for strength*/
	STRENGTH("Strength"),
	/**used for Perception*/
	PERCEPTION("Perception"),
	/**used for Agility*/
	AGILITY("Agility"),
	/**used for Endurance*/
	ENDURANCE("Endurance"),
	/**used for Intelligence*/
	INTELLIGENCE("Intelligence"),
	/**used for Wisdom*/
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
