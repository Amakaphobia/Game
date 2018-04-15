package test;

import entity.basic.attributeSet.AttributeSet;
import entity.basic.attributeSet.Attributes;

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
	}

}
