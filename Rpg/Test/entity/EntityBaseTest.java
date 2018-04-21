package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Parent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import entity.basic.EntityBase;

@SuppressWarnings("javadoc")
class EntityBaseTest {
	static String name = "Harry Dresden";
	static String bild = "C:/bild.png";
	static String desc = "Wizard Detective" ;

	static EntityBase eb1;
	static EntityBase eb2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		eb1 = new EntityBase(name, bild, desc) {
			@Override
			public Parent getInfoView() {return null;}
		};
		eb2 = new EntityBase(name, bild, desc) {
			@Override
			public Parent getInfoView() {return null;}
		};
	}

	@Test
	void testHashCode() {
		assertEquals(eb1.hashCode(), eb1.hashCode(), () -> "Not Equal to itself");
		assertNotEquals(eb1.hashCode(), eb2.hashCode(), () -> "HashColide");
	}

	@Test
	void testIsLiving() {
		eb1.setLiving(true);
		assertTrue(eb1.isLiving(), () -> "Expected Living");
		eb2.setLiving(false);
		assertTrue(!eb2.isLiving(), () -> "Expected Dead");
	}

	@Test
	void testIdProperty() {
		assertNotEquals(eb1.getId(), eb2.getId(), () -> "Id Colide");
	}

	@Test
	void testNameProperty() {
		assertEquals(name, eb1.getName(), () -> "Name Wrong");
	}

	@Test
	void testDescriptionProperty() {
		assertEquals(desc, eb1.getDescription(), () -> "Desc Wrong");
	}

	@Disabled("Bild2 not yet Implemented")
	@Test
	void testGetBild() {
	}

	@Test
	void testEqualsObject() {
		assertEquals(eb1, eb1, () -> "not equal to self");
		assertNotEquals(eb1, eb2, () -> "equal to different");
	}

	@Test
	void testToString() {
		assertEquals("EntityBase: 0 Harry Dresden", eb1.toString(), () -> "toString Fucked up");
	}

}
