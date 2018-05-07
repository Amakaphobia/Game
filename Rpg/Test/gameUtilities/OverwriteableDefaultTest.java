package gameUtilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import common.OverwriteableDefault;

@SuppressWarnings("javadoc")
class OverwriteableDefaultTest {

	@Test
	void testHashCode() {
		OverwriteableDefault<Integer> od1 = new OverwriteableDefault<>(5);
		OverwriteableDefault<Integer> od1b = new OverwriteableDefault<>(5);
		OverwriteableDefault<Integer> od2 = new OverwriteableDefault<>(2);
		int hash = od1.hashCode();
		assertEquals(hash, od1.hashCode());
		assertEquals(hash, od1b.hashCode());
		assertNotEquals(hash, od2.hashCode());

		od1.set(6);
		assertNotEquals(hash, od1.hashCode());
	}

	@Test
	void testEqualsObject() {
		OverwriteableDefault<Integer> od1 = new OverwriteableDefault<>(5);
		OverwriteableDefault<Integer> od1b = new OverwriteableDefault<>(5);

		assertEquals(od1, od1);
		assertEquals(od1, od1b);

		od1.set(6);
		assertNotEquals(od1, od1b);

		od1b.set(6);
		assertEquals(od1, od1b);

		od1.clearOverwrite();
		od1b.clearOverwrite();
		assertEquals(od1, od1b);
	}

	@Test
	void testToString() {
		OverwriteableDefault<Integer> od1 = new OverwriteableDefault<>(5);
		assertEquals("Default: 5\r\nCurrent: Default", od1.toString());
		od1.set(6);
		assertEquals("Default: 5\r\nCurrent: 6", od1.toString());
	}

	@Test
	void testIsOverwritten() {
		OverwriteableDefault<Integer> od1 = new OverwriteableDefault<>(5);
		assertEquals(false, od1.isOverwritten());
		od1.set(6);
		assertEquals(true, od1.isOverwritten());
		od1.clearOverwrite();
		assertEquals(false, od1.isOverwritten());
	}

	@Test
	void testGet() {
		OverwriteableDefault<Integer> od1 = new OverwriteableDefault<>(5);
		assertTrue(5 == od1.get());

		od1.set(6);
		assertTrue(6 == od1.get());
	}
}
