package entity.basic.skillSet;

import java.util.Optional;

/**
 * this Interface will be implemented by all Skillsetimplementations. 
 * It handles the accessing adding, removing and leveling of skills.
 * @author hdaiv_000
 *
 */
public interface I_SkillSet extends Iterable<Skill>{
	/**
	 * This method is used to get a Optional ontaining a searched Skill
	 * @param id a {@link Skills} enum that defines what skill is searched
	 * @return a optiona containing the found skill or emptyy if the skill isnt part of this set.
	 */
	public abstract Optional<Skill> getSkill(Skills id);
	/**
	 * this method is used to add a Skill at a certain level to the skill set.
	 * @param id the {@link Skills} Identifier of the skill you want to add
	 * @param level the level you want the skill to be at initially.
	 */
	public abstract void addSkill(Skills id, int level);
	/**
	 * This method is remove a known SKill from the list completly
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
	 * This method is used to get a integer containing the actual lvl of the given {@link Skills}
	 * @param id the Skill you are looking for
	 * @return the level oof the skill as an Integer. 0 if it is not known.
	 */
	public default int getSkillLevel(Skills id) {
		return this.getSkill(id).orElse(Skill.empty()).getValue();
	}
	
	/**
	 * This method is used to train a learned skill by given levels
	 * @param id the {@link Skills} you want to train
	 * @param levelGain how many levels (positive and negative) you want to train
	 */
	public default void trainSkill(Skills id, int levelGain) {
		int oldLevel = this.getSkillLevel(id);
		int testLevel = oldLevel + levelGain;
		
		final int newLevel = testLevel > 0 ? testLevel : 0;
		
		this.getSkill(id).ifPresent(skill -> skill.setValue(newLevel));		
	}
}
