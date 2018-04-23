package model.entity.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;

import entity.basic.attributeSet.Attribute;
import entity.basic.attributeSet.AttributeSet;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.skillsattributes.Attributes;

@SuppressWarnings("javadoc")
class AttributeSetTest {

	AttributeSet allZero = new AttributeSet();
	AttributeSet allFive = new AttributeSet(5);
	AttributeSet mixed = new AttributeSet(
			10, //Strength
			12, //Dex
			9,  //const
			10, //int
			16, //wis
			20);//char

	@Test
	void testAddRemoveDecorator() {
		int [] arr = {1, 3, 5};

		List<I_AttributeSet> ll =
				IntStream.of(arr)
					.mapToObj(AttributeSet::new)
					.collect(toList());

		for(int i = 0; i < arr.length; i++)
			this.testSetAgainstValue(ll.get(i), arr[i], () -> "Initial Fuckup");

		ll.get(0).addDecorator(ll.get(1));
		this.testSetAgainstValue(ll.get(0), 4, () -> "Adding first decorator");

		ll.get(0).addDecorator(ll.get(2));
		this.testSetAgainstValue(ll.get(0), 9, () -> "Adding second decorator");

		ll.get(0).removeDecorator(ll.get(1));
		this.testSetAgainstValue(ll.get(0), 6, () -> "Removing middle decorator");

		ll.get(0).removeDecorator(ll.get(2));
		this.testSetAgainstValue(ll.get(0), 1, () -> "Removing last decorator");

	}

	void testSetAgainstValue(I_AttributeSet set, int value, Supplier<String> message) {
		for(Attribute e : set)
			assertEquals(value, e.getValue(), message);
	}

	@Test
	void testHashCode() {
		assertEquals(allZero.hashCode(), new AttributeSet().hashCode());
		assertEquals(allFive.hashCode(), new AttributeSet(5).hashCode());
		assertNotEquals(allZero.hashCode(), allFive.hashCode());
		assertEquals(mixed.hashCode(), mixed.hashCode());
	}

	@Test
	void testGetAttribute() {
		assertEquals(new Attribute(Attributes.STRENGTH, 10), mixed.getAttribute(Attributes.STRENGTH));
		assertEquals(new Attribute(Attributes.DEXTERITY, 0), allZero.getAttribute(Attributes.DEXTERITY));
	}

	@Test
	void testToString() {
		assertEquals(
			"Strength: 0 Dexterity: 0 Constitution: 0 Intelligence: 0 Wisdom: 0 Charisma: 0",
			allZero.toString());
		assertEquals(
			"Strength: 10 Dexterity: 12 Constitution: 9 Intelligence: 10 Wisdom: 16 Charisma: 20",
			mixed.toString());
	}

	@Test
	void testEqualsObject() {
		assertEquals(allZero, new AttributeSet());
		assertEquals(allFive, allFive);
		assertNotEquals(allZero, allFive);
		assertEquals(mixed, mixed);
	}

}
