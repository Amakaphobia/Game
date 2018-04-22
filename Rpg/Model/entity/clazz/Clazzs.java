package entity.clazz;

import java.io.Serializable;
import java.util.List;

import entity.basic.enums.skillsattributes.Skills;

/**
 * Enum identifying available classes.
 * @author Dave
 *
 */
public enum Clazzs implements Serializable
{
	/**
	 * Mage Class
	 */
	MAGE("Mage", "1d6"),

	/**
	 * Warrior Class
	 */
	WARRIOR("Warrior", "1d10");

	/**the Class Name*/
	private String id;
	/**the hitdie of the class*/
	private String hitDieCode;

	/**
	 * Constructor
	 * @param id the class name
	 * @param hitDieCode the class' hitdie code
	 */
	private Clazzs(String id, String hitDieCode) {
		this.id = id;
		this.hitDieCode = hitDieCode;
	}

	/**
	 * Access the ClassName
	 * @return the Class Name
	 */
	public String getId() { return this.id; }

	/**
	 * Access the hitdie code
	 * @return the hitdie code
	 */
	public String getHitDieCode() { return this.hitDieCode; }

	/**
	 * This Method is used to get a List of all {@link Skills}, which are class skills of this class
	 * @return List containing all ClassSkills
	 */
	public List<Skills> getClassSkills(){
		return ClazzSkillListGenerator.getClassSkillsFor(this);
	}
}
