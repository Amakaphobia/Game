package entity.clazz;

import java.util.List;

import entity.basic.skillSet.Skills;

public enum Clazzs 
{
	MAGE("Mage", "1d6"),
	WARRIOR("Warrior", "1d10");
	
	private String id;
	private String hitDieCode;
	
	private Clazzs(String id, String hitDieCode) {
		this.id = id;
		this.hitDieCode = hitDieCode;
	}
	
	public String getId() { return this.id; }
	public String getHitDieCode() { return this.hitDieCode; }
	
	public List<Skills> getClassSkills(){
		return ClazzSkillListGenerator.getClassSkillsFor(this);
	}
}
