package entity.basic.skillSet;

import java.io.Serializable;
import java.util.Optional;

import common.decorator.I_SimpleDecoratorContainer;
import entity.basic.common.enums.skillsattributes.Skills;

/**
 * this Interface will be implemented by all Skillset implementations.
 * It handles the accessing adding, removing and leveling of skills.
 * @author hdaiv_000
 *
 */
public interface I_SkillSet extends I_SimpleDecoratorContainer<I_SkillSet>, Serializable, Iterable<I_Skill>{
	/**
	 * This method is used to get a Optional containing a searched Skill
	 * @param id a {@link Skills} enum that defines what skill is searched
	 * @return a optional containing the found skill or empty if the skill is not part of this set.
	 */
	public abstract Optional<I_Skill> getSkill(Skills id);
	/**
	 * this method is used to add a Skill at a certain level to the skill set.
	 * @param id the {@link Skills} Identifier of the skill you want to add
	 * @param level the level you want the skill to be at initially.
	 */
	public abstract void addSkill(Skills id, int level);
	/**
	 * This method is remove a known SKill from the list completely
	 * @param id the {@link Skills} enum that defines the skill you want to remove
	 */
	public abstract void removeSkill(Skills id);
	/**
	 * this method is used to add a Skill at lvl1 to the skillset. See {@link I_SkillSet#addSkill(Skills, int)}.
	 * @param id the id of the skill you want to add
	 */
	public default void addSkill(Skills id) {
		this.addSkill(id, 1);
	}
	/**
	 * test if a given skill is part of this skillset.
	 * @param id the {@link Skills} enum that defines the searched skill
	 * @return true if found false if not
	 */
	public default boolean hasSkill(Skills id) {
		return this.getSkill(id).isPresent();
	}

	/**
	 * This method is used to get a integer containing the actual level of the given {@link Skills}
	 * @param id the Skill you are looking for
	 * @return the level of the skill as an Integer. 0 if it is not known.
	 */
	public default int getSkillLevel(Skills id) {
		return this.getSkill(id).map(s -> s.getValue()).orElse(0);
	}

	/**
	 * This method is used to train a learned skill by given levels
	 * @param id the {@link Skills} you want to train
	 * @param levelGain how many levels (positive and negative) you want to train
	 */
	public default void trainSkill(Skills id, int levelGain) {
		int oldLevel = this.getSkillLevel(id);
		int testLevel = oldLevel + levelGain;

		if(testLevel > 0) {
			Optional<I_Skill> s = this.getSkill(id);
			if(s.isPresent())
				s.get().setValue(testLevel);
			else
				this.addSkill(id, levelGain);}
		else
			this.removeSkill(id);
	}
}
