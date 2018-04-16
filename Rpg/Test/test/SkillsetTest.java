package test;

import entity.basic.attributeSet.AttributeSet;
import entity.basic.attributeSet.Attributes;
import entity.basic.skillSet.SkillSet;
import entity.basic.skillSet.Skills;

@SuppressWarnings("javadoc")
public class SkillsetTest {

	public static void main(String[] args) {
		AttributeSet s = new AttributeSet();
		System.out.println(s);
		s.setStrength(1);
		System.out.println(s);
		s.setAgility(5);
		System.out.println(s);

		Attributes e = Attributes.AGILITY;
		Attributes f = Attributes.AGILITY;

		System.out.println(e.equals(f));
		System.out.println(e == f);
		
		
		SkillSet ss = new SkillSet();
		ss.addSkill(Skills.SWIMMING);
		ss.trainSkill(Skills.SWIMMING, 3);
		ss.addSkill(Skills.ACROBATIC, 2);
		
		System.out.println(ss);
		
		ss.removeSkill(Skills.SWIMMING);
		ss.trainSkill(Skills.ACROBATIC, -1);
		
		System.out.println(ss);
	}

}
