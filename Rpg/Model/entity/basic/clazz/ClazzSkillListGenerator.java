package entity.basic.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.basic.common.enums.clazz.Clazzs;
import entity.basic.common.enums.skillsattributes.Skills;

/**
 * Mock DB class to find Skill-Lists for Class Skills
 * @author Dave
 *
 */
public class ClazzSkillListGenerator
{
	/**
	 * This method returns the Class Skills for given {@link Clazzs} id
	 * @param id the {@link Clazzs} id
	 * @return {@link List} containing all Class-{@link Skills}.
	 */
	public static List<Skills> getClassSkillsFor(Clazzs id){
		switch(id) {
			case WARRIOR:
				return getWarriorSkills();
			case MAGE:
				return getMageSkills();
			default:
				return new ArrayList<>();
		}
	}

	/**
	 * internal method for Class Skills of the Warrior-Class
	 * @return {@link List} containing all Class-{@link Skills}.
	 */
	private static List<Skills> getWarriorSkills(){
		return Arrays.asList(Skills.ACROBATIC);
	}

	/**
	 * internal method for Class Skills of the Mage-Class
	 * @return {@link List} containing all Class-{@link Skills}.
	 */
	private static List<Skills> getMageSkills(){
		return Arrays.asList(Skills.SWIM);
	}
}
