package entity.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.basic.skillSet.Skills;

public class ClazzSkillListGenerator 
{
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
	
	private static List<Skills> getWarriorSkills(){
		return Arrays.asList(Skills.ACROBATIC);
	}
	
	private static List<Skills> getMageSkills(){
		return Arrays.asList(Skills.SWIMMING);
	}
}
