package entity.basic.skillSet;

import java.util.Optional;

public interface I_SkillSet extends Iterable<Skill>{
	public abstract Optional<Skill> getSkill(Skills id);
	public abstract void addSkill(Skills id, int level);
	public abstract void removeSkill(Skills id);
	
	public default void addSkill(Skills id) {
		this.addSkill(id, 1);
	}
	
	public default boolean hasSkill(Skills id) {
		return this.getSkill(id).isPresent();
	}
	
	public default int getSkillLevel(Skills id) {
		return this.getSkill(id).orElse(Skill.empty()).getValue();
	}
	
	public default void trainSkill(Skills id, int levelGain) {
		int oldLevel = this.getSkillLevel(id);
		int testLevel = oldLevel + levelGain;
		
		final int newLevel = testLevel > 0 ? testLevel : 0;
		
		this.getSkill(id).ifPresent(skill -> skill.setValue(newLevel));		
	}
}
