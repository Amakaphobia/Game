package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.Attributes;

@SuppressWarnings("javadoc")
class AttributeTest {

//	static List<Attribute> list;
	static Attribute perc2 = new Attribute(Attributes.PERCEPTION, 2);
	static Attribute perc1 = new Attribute(Attributes.PERCEPTION, 1);
	static Attribute wis4 = new Attribute(Attributes.WISDOM, 4);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
//		list = IntStream.rangeClosed(1, 45)
//				.mapToObj(i -> new Attribute(Attributes.STRENGTH, i))
//				.collect(Collectors.toList());
	}

	@Test
	void testHashCode() {
		assertEquals(perc2.hashCode(), new Attribute(Attributes.PERCEPTION, 2).hashCode());
		assertEquals(perc2.hashCode(), perc2.hashCode());
		assertNotEquals(perc2.hashCode(), wis4.hashCode());
	}

	@Test
	void testGetValue() {
		assertEquals(2, perc2.getValue());
		assertEquals(4, wis4.getValue());
	}

	@Test
	void testSetValue() {
		Attribute tmp = new Attribute(Attributes.PERCEPTION, 2);
		tmp.setValue(4);
		assertEquals(4, tmp.getValue());
	}

	@Test
	void testGetDerivedModifier() {
		assertEquals(-5, perc1.getDerivedModifier());
		assertEquals(-4, perc2.getDerivedModifier());
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
		assertEquals("- 5", perc1.getDerivedAttributeModifierAsString());
		assertEquals("- 4", perc2.getDerivedAttributeModifierAsString());
		assertEquals("", new Attribute(Attributes.STRENGTH, 10).getDerivedAttributeModifierAsString());
		assertEquals("+ 10", new Attribute(Attributes.STRENGTH, 30).getDerivedAttributeModifierAsString() );
	}

	@Test
	void testGetName() {
		assertEquals("Wisdom", wis4.getName());
		assertEquals("Perception", perc1.getName());
		assertEquals(perc1.getName(), perc2.getName());
	}

	@Test
	void testEqualsObject() {
		assertEquals(perc1, new Attribute(Attributes.PERCEPTION, 1));
		assertEquals(wis4, new Attribute(Attributes.WISDOM, 4));
		assertEquals(perc1, perc1);
		assertNotEquals(perc1, wis4);
	}

	@Test
	void testToString() {
		assertEquals("Perception: 1", perc1.toString());
		assertEquals("Wisdom: 4", wis4.toString());
	}

}
