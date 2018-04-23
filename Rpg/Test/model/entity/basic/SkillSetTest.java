package model.entity.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.basic.common.enums.skillsattributes.Skills;
import entity.basic.skillSet.Skill;
import entity.basic.skillSet.SkillSet;

@SuppressWarnings("javadoc")
class SkillSetTest {

	static SkillSet s1 = new SkillSet();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		s1.addSkill(Skills.ACROBATIC, 2);
		s1.addSkill(Skills.SWIMMING, 3);

	}


	@Test
	void testDecorating() {
		SkillSet s2 = new SkillSet();
	}

	@Test
	void testHashCode() {
		assertEquals(s1.hashCode(), s1.hashCode());

		SkillSet tmp = new SkillSet(
			Arrays.asList(
				new Skill(Skills.ACROBATIC, 2),
				new Skill(Skills.SWIMMING, 3)));

		assertEquals(s1.hashCode(), tmp.hashCode());

		tmp.trainSkill(Skills.ACROBATIC, 2);
		assertNotEquals(s1.hashCode(), tmp.hashCode());
	}

	@Test
	void testSize() {
		assertEquals(2, s1.size());
	}

	@Test
	void testGetSkill() {
		Skill tmp = new Skill(Skills.ACROBATIC, 2);
		assertEquals(tmp, s1.getSkill(Skills.ACROBATIC).get());
	}

	@Test
	void testAddSkill() {
		SkillSet ss = new SkillSet();
		ss.addSkill(Skills.ACROBATIC);
		assertEquals(1, ss.getSkillLevel(Skills.ACROBATIC));
	}

	@Test
	void testRemoveSkill() {
		SkillSet ss = new SkillSet();
		ss.addSkill(Skills.ACROBATIC);
		ss.removeSkill(Skills.ACROBATIC);

		assertEquals(0, ss.size());
		assertEquals(new SkillSet(), ss);
	}

	@Test
	void testToString() {
		assertEquals("{SWIMMING=Swimming: 3, ACROBATIC=Acrobatic: 2}", s1.toString());
	}

	@Test
	void testEqualsObject() {
		SkillSet tmp = new SkillSet(
				Arrays.asList(
					new Skill(Skills.ACROBATIC, 2),
					new Skill(Skills.SWIMMING, 3)));
		assertEquals(tmp, s1);
		tmp.trainSkill(Skills.ACROBATIC, 1);

		assertNotEquals(tmp, s1);
	}

}
