package model.entity.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.common.enums.skillsattributes.Skills;
import entity.basic.skillSet.Skill;

@SuppressWarnings("javadoc")
class SkillTest {

	static Skill e1;
	static Skill e2;
	static Skill e3;
	static Skill e4;
	static Skill e5;
	static Skill e6;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		e1 = new Skill(Skills.ACROBATIC, 1);
		e2 = new Skill(Skills.ACROBATIC, 2);
		e3 = new Skill(Skills.ACROBATIC, 3);
		e4 = new Skill(Skills.SWIMMING, 2);
		e5 = new Skill(Skills.SWIMMING, 3);
		e6 = Skill.empty();
	}


	@Test
	void testDecorating() {

	}

	@Test
	void testHashCode() {
		assertEquals(e1.hashCode(), new Skill(Skills.ACROBATIC, 1).hashCode());
		assertEquals(e6.hashCode(), Skill.empty().hashCode());
		assertNotEquals(e1.hashCode(), e2.hashCode());
	}

	@Test
	void testIsUntrained() {
		assertTrue(e4.isUntrained());
		assertTrue(!e1.isUntrained());
	}

	@Test
	void testGetName() {
		assertEquals(e1.getName(), e2.getName());
		assertNotEquals(e6.getName(), e1.getName());
	}

	@Test
	void testGetNameId() {
		assertEquals(e1.getNameId(), e2.getNameId());
		assertNotEquals(e6.getNameId(), e1.getNameId());
	}

	@Test
	void testGetValue() {
		assertEquals(e2.getValue(), e4.getValue());
	}

	@Test
	void testSetValue() {
		Skill tmp = new Skill(Skills.ACROBATIC, 1);
		tmp.setValue(3);
		assertEquals(3, tmp.getValue().intValue());
	}

	@Test
	void testGetMainAttribute() {
		assertEquals(Attributes.DEXTERITY, e1.getMainAttribute());
	}

	@Test
	void testEqualsObject() {
		assertEquals(new Skill(Skills.ACROBATIC, 1), e1);
		assertNotEquals(e2, e1);
	}

	@Test
	void testToString() {
		assertEquals("Acrobatic: 1", e1.toString());
		assertEquals("Acrobatic: 2", e2.toString());
	}

	@Test
	void testEmpty() {
		assertEquals(e6, Skill.empty());
	}

}
