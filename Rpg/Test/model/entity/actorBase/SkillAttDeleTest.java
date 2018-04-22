package model.entity.actorBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import boxes.Pair;
import entity.actorBase.container.SkillAttDele;
import entity.basic.attributeSet.AttributeSet;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.enums.skillsattributes.Attributes;
import entity.basic.enums.skillsattributes.Skills;
import entity.basic.skillSet.I_SkillSet;
import entity.basic.skillSet.Skill;
import entity.basic.skillSet.SkillSet;

@SuppressWarnings("javadoc")
class SkillAttDeleTest{

	private class Mockery{
		I_SkillSet ss = new SkillSet(Arrays.asList(
				new Skill(Skills.ACROBATIC,2),
				new Skill(Skills.SWIMMING,5)
				));
		I_AttributeSet as = new AttributeSet(10);

		SkillAttDele sad = new SkillAttDele(null, ss, as);
	}

	@Test
	void testHashCode() {
		Mockery m = new Mockery();
		assertEquals(m.sad.hashCode(), m.sad.hashCode());
		Mockery n = new Mockery();
		assertEquals(m.sad.hashCode(), n.sad.hashCode());
		n.ss.trainSkill(Skills.ACROBATIC, 2);
		assertNotEquals(m.sad.hashCode(), n.sad.hashCode());
	}

	@Test
	void testSetSkillSet() {
		I_SkillSet ss = new SkillSet(Arrays.asList(
				new Skill(Skills.ACROBATIC,4),
				new Skill(Skills.SWIMMING,5)
				));
		Mockery m = new Mockery();
		assertNotEquals(ss, m.sad.getSkillSet());
		m.sad.setSkillSet(ss);
		assertEquals(ss, m.sad.getSkillSet());
	}

	@Test
	void testSetAttributeSet() {
		I_AttributeSet as = new AttributeSet(8);
		Mockery m = new Mockery();
		assertNotEquals(as, m.sad.getAttributeSet());
		m.sad.setAttributeSet(as);
		assertEquals(as, m.sad.getAttributeSet());
	}

	@Test
	void testGetSkill() {
		Mockery m = new Mockery();
		assertEquals(m.ss.getSkill(Skills.ACROBATIC), m.sad.getSkill(Skills.ACROBATIC));
	}

	@Test
	void testGetSkillLevel() {
		Mockery m = new Mockery();
		assertEquals(m.ss.getSkillLevel(Skills.ACROBATIC), m.sad.getSkillLevel(Skills.ACROBATIC));
	}

	@Test
	void testTrainSkill() {
		Mockery m = new Mockery();
		I_SkillSet ss = new SkillSet(Arrays.asList(
				new Skill(Skills.ACROBATIC,2),
				new Skill(Skills.SWIMMING,5)
				));

		m.sad.trainSkill(Skills.ACROBATIC, 1);
		ss.trainSkill(Skills.ACROBATIC, 1);

		assertEquals(m.sad.getSkillSet(), ss);
	}

	@Test
	void testRemoveSkill() {
		Mockery m = new Mockery();
		m.sad.removeSkill(Skills.ACROBATIC);
		assertEquals(new SkillSet(Arrays.asList(new Skill(Skills.SWIMMING, 5))), m.sad.getSkillSet());
	}

	@Test
	@Disabled
	void testDoCheckSkilledActorBaseSkillsIntInt() {
		//TODO ehhh?
	}

	@Test
	void testGetAttribute() {
		Mockery m = new Mockery();
		Stream.of(Attributes.values())
			.map(a -> new Pair<>(m.as.getAttribute(a), m.sad.getAttribute(a)))
			.forEach(p -> assertEquals(p.getKey(), p.getValue()));
	}

	@Test
	void testGetAttributeLevel() {
		Mockery m = new Mockery();
		Stream.of(Attributes.values())
			.map(a -> new Pair<>(m.as.getAttribute(a).getValue(), m.sad.getAttributeLevel(a)))
			.forEach(p -> assertEquals(p.getKey(), p.getValue()));
	}

	@Test
	void testChangeAttribute() {
		IntStream.range(-5, 6)
			.forEach(i -> {
				final Mockery m = new Mockery();
				final int startingLevel = m.sad.getAttributeLevel(Attributes.CHARISMA);
				m.sad.changeAttribute(Attributes.CHARISMA, i);

				assertEquals(startingLevel + i, m.sad.getAttributeLevel(Attributes.CHARISMA));
			});
	}

	@Test
	@Disabled
	void testDoCheckSkilledActorBaseAttributesIntInt() {
		//TODO ehhh?
	}

	@Test
	void testGetDerivedAttributeModifierAsString() {
		Mockery m = new Mockery();
		Stream.of(Attributes.values())
			.map(a -> new Pair<>(
					m.as.getDerivedAttributeModifierAsString(a),
					m.sad.getDerivedAttributeModifierAsString(a)))
			.forEach(p -> assertEquals(p.getKey(), p.getValue()));
	}

	@Test
	void testEqualsObject() {
		Mockery m1 = new Mockery();
		Mockery m2 = new Mockery();

		assertEquals(m1.sad, m1.sad);
		assertEquals(m1.sad, m2.sad);
		m2.sad.changeAttribute(Attributes.CHARISMA, 1);
		assertNotEquals(m1.sad, m2.sad);
	}

	@Test
	void testToString() {
		Mockery m1 = new Mockery();
		String s = "Strength: 10 Dexterity: 10 Constitution: 10 Intelligence: 10 Wisdom: 10 Charisma: 10\n";
		s = s.concat("[Acrobatic: 2, Swimming: 5]");
		assertEquals(s, m1.sad.toString());
	}

}
