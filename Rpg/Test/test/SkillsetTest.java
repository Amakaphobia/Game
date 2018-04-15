package test;

import entity.basic.attributeset.Attributeset;

public class SkillsetTest {

	public static void main(String[] args) {
		Attributeset s = new Attributeset();
		System.out.println(s);
		s.setStrength(1);
		System.out.println(s);
		s.setAgility(5);
		System.out.println(s);

	}

}
