package entity.basic.common.enums.clazz;

import java.io.Serializable;
import java.util.List;

import dicemachine.DiceCodeBase;
import dicemachine.I_DiceCode;
import entity.basic.clazz.ClazzSkillListGenerator;
import entity.basic.common.enums.skillsattributes.Skills;

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
	MAGE("Mage", 6),

	/**
	 * Warrior Class
	 */
	WARRIOR("Warrior", 10);

	/**the Class Name*/
	private String id;
	/**the hitdie of the class*/
	private DiceCodeBase hitDieCode;

	/**
	 * Constructor
	 * @param id the class name
	 * @param diceSize size of the hitdie
	 */
	private Clazzs(String id, int diceSize) {
		this.id = id;
		this.hitDieCode = I_DiceCode.roll(1, diceSize);
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
	public DiceCodeBase getHitDieCode() { return this.hitDieCode; }

	/**
	 * This Method is used to get a List of all {@link Skills}, which are class skills of this class
	 * @return List containing all ClassSkills
	 */
	public List<Skills> getClassSkills(){
		return ClazzSkillListGenerator.getClassSkillsFor(this);
	}
}
