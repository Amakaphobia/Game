package model.entity.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import entity.basic.attributeSet.Attribute;
import entity.basic.common.enums.skillsattributes.Attributes;

@SuppressWarnings("javadoc")
class AttributeTest {

	static Attribute dex2 = new Attribute(Attributes.DEXTERITY, 2);
	static Attribute dex1 = new Attribute(Attributes.DEXTERITY, 1);
	static Attribute wis4 = new Attribute(Attributes.WISDOM, 4);

	@Test
	void testAddAndRemoveDecorator() {
		Attribute a1 = new Attribute(Attributes.DEXTERITY, 2);
		Attribute a2 = new Attribute(Attributes.DEXTERITY, 1);
		Attribute a3 = new Attribute(Attributes.DEXTERITY, 5);

		assertEquals(2, a1.getValue().intValue(), () -> "Initial Fuck up");

		a1.addDecorator(a3);

		assertEquals(7, a1.getValue().intValue(), () -> "Docrator first adding");

		a1.addDecorator(a2);

		assertEquals(8, a1.getValue().intValue(), () -> "Decorator second adding");

		a1.removeFirstDecorator(a3);

		assertEquals(3, a1.getValue().intValue(), () -> "remove middle decorator");

		a1.removeFirstDecorator(new Attribute(Attributes.DEXTERITY, 4));

		assertEquals(3, a1.getValue().intValue(), () -> "remove nonexistant decorator");

		a1.removeFirstDecorator(a2);

		assertEquals(2, a1.getValue().intValue(), () -> "remove last decorator");

	}

	@Test
	void testHashCode() {
		assertEquals(dex2.hashCode(), new Attribute(Attributes.DEXTERITY, 2).hashCode());
		assertEquals(dex2.hashCode(), dex2.hashCode());
		assertNotEquals(dex2.hashCode(), wis4.hashCode());
	}

	@Test
	void testGetValue() {
		assertEquals(2, dex2.getValue().intValue());
		assertEquals(4, wis4.getValue().intValue());
	}

	@Test
	void testSetValue() {
		Attribute tmp = new Attribute(Attributes.DEXTERITY, 2);
		tmp.setValue(4);
		assertEquals(4, tmp.getValue().intValue());
	}

	@Test
	void testGetDerivedModifier() {
		assertEquals(-5, dex1.getDerivedModifier());
		assertEquals(-4, dex2.getDerivedModifier());
		assertEquals(-3, wis4.getDerivedModifier());

		assertEquals(17, new Attribute(Attributes.STRENGTH, 45).getDerivedModifier());
		assertEquals(17, new Attribute(Attributes.STRENGTH, 44).getDerivedModifier());

		assertEquals(-1, new Attribute(Attributes.STRENGTH, 9).getDerivedModifier());
		assertEquals(0, new Attribute(Attributes.STRENGTH, 10).getDerivedModifier());
		assertEquals(0, new Attribute(Attributes.STRENGTH, 11).getDerivedModifier());
		assertEquals(1, new Attribute(Attributes.STRENGTH, 12).getDerivedModifier());

		assertEquals(10, new Attribute(Attributes.STRENGTH, 30).getDerivedModifier());
		assertEquals(10, new Attribute(Attributes.STRENGTH, 31).getDerivedModifier());

		assertEquals(13, new Attribute(Attributes.STRENGTH, 36).getDerivedModifier());
		assertEquals(13, new Attribute(Attributes.STRENGTH, 37).getDerivedModifier());

	}

	@Test
	void testGetDerivedAttributeModifierAsString() {
		assertEquals("- 5", dex1.getDerivedAttributeModifierAsString());
		assertEquals("- 4", dex2.getDerivedAttributeModifierAsString());
		assertEquals("", new Attribute(Attributes.STRENGTH, 10).getDerivedAttributeModifierAsString());
		assertEquals("+ 10", new Attribute(Attributes.STRENGTH, 30).getDerivedAttributeModifierAsString() );
	}

	@Test
	void testGetName() {
		assertEquals("Wisdom", wis4.getNameId());
		assertEquals("Dexterity", dex1.getNameId());
		assertEquals(dex1.getNameId(), dex2.getNameId());
	}

	@Test
	void testEqualsObject() {
		assertEquals(dex1, new Attribute(Attributes.DEXTERITY, 1));
		assertEquals(wis4, new Attribute(Attributes.WISDOM, 4));
		assertEquals(dex1, dex1);
		assertNotEquals(dex1, wis4);
	}

	@Test
	void testToString() {
		assertEquals("Dexterity: 1", dex1.toString());
		assertEquals("Wisdom: 4", wis4.toString());
	}

}
