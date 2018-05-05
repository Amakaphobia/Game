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
		s1.addSkill(Skills.SWIM, 3);

	}

	@Test
	void testDecorating() {
		SkillSet s2 = new SkillSet();
		s2.trainSkill(Skills.KNOWLEDGE_ARCANA, 1);

		SkillSet s3 = new SkillSet();
		s3.trainSkill(Skills.KNOWLEDGE_ARCANA, 1);
		s3.trainSkill(Skills.ACROBATIC, 2);

		SkillSet s3b = new SkillSet();
		s3b.trainSkill(Skills.KNOWLEDGE_ARCANA, 1);
		s3b.trainSkill(Skills.ACROBATIC, 2);

		s3.addDecorator(s1);
		assertEquals(4, s3.getSkillLevel(Skills.ACROBATIC));
		assertEquals(1, s3.getSkillLevel(Skills.KNOWLEDGE_ARCANA));

		s3.addDecorator(s2);
		assertEquals(2, s3.getSkillLevel(Skills.KNOWLEDGE_ARCANA));

		s3.removeFirstDecorator(s1);
		assertEquals(2, s3.getSkillLevel(Skills.ACROBATIC));
		assertEquals(2, s3.getSkillLevel(Skills.KNOWLEDGE_ARCANA));

		s3.removeFirstDecorator(s2);
		assertEquals(s3b, s3);
	}

	@Test
	void testHashCode() {
		assertEquals(s1.hashCode(), s1.hashCode());

		SkillSet tmp = new SkillSet(
			Arrays.asList(
				new Skill(Skills.ACROBATIC, 2),
				new Skill(Skills.SWIM, 3)));

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
		assertEquals("{ACROBATIC=Acrobatic: 2, SWIM=Swim: 3}", s1.toString());
	}

	@Test
	void testEqualsObject() {
		SkillSet tmp = new SkillSet(
				Arrays.asList(
					new Skill(Skills.ACROBATIC, 2),
					new Skill(Skills.SWIM, 3)));
		assertEquals(tmp, s1);
		tmp.trainSkill(Skills.ACROBATIC, 1);

		assertNotEquals(tmp, s1);
	}

}
