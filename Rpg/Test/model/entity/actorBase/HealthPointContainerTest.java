package model.entity.actorBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.collections.ObservableList;

import org.junit.jupiter.api.Test;

import boxes.Pair;
import entity.actorBase.container.HealthPointContainer;
import entity.actorBase.container.I_HasHp;

@SuppressWarnings("javadoc")
class HealthPointContainerTest {

	public HealthPointContainer buildOne() {
		I_HasHp Parent = new I_HasHp() {
			@Override public void onHealthZero() {}
			@Override public void onHealthNoLongerZero() {}
		};

		HealthPointContainer hpc = new HealthPointContainer(Parent);

		hpc.addHitDieMax("1d6");
		hpc.addHitDieMax("1d6");

		return hpc;
	}

	@Test
	void testGetHpList() {
		ObservableList<?> ll = this.buildOne().getHpList();
		assertEquals(2, ll.size());
		assertEquals("1d6", ((Pair<?,?>)ll.get(0)).getKey() );
		assertEquals(6, ((Pair<?,?>)ll.get(0)).getValue() );
	}

	@Test
	void testGetAllCodes() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDie("1d6");
		Hpc.addHitDie("1d6");
		Hpc.addHitDie("1d6");
		Hpc.addHitDie("1d6");
		Hpc.addHitDie("1d8");
		Hpc.addHitDie("2d8");

		assertEquals(Hpc.getAllCodes().get(0), "6d6");
		assertEquals(Hpc.getAllCodes().get(1), "3d8");
	}

	@Test
	void testGetMaxHealth() {
		HealthPointContainer Hpc = this.buildOne();
		assertEquals(12, Hpc.getMaxHealth());
		Hpc.addHitDie("1d6");
		assertTrue(Hpc.getMaxHealth() <= 18 && Hpc.getMaxHealth() > 12);
	}

	@Test
	void testGetCurrentHealth() {
		HealthPointContainer Hpc = this.buildOne();

		int testHp = Hpc.getMaxHealth();

		assertEquals(testHp, Hpc.getCurrentHealth());

		int dmg = 6;
		testHp -= dmg;
		Hpc.takeDamage(dmg);
		assertEquals(testHp, Hpc.getCurrentHealth());

		testHp += dmg;
		Hpc.takeHeal(dmg);
		assertEquals(testHp, Hpc.getCurrentHealth());
	}

	@Test
	void testTakeDamage() {
		HealthPointContainer Hpc = this.buildOne();
		int maxHp = Hpc.getMaxHealth();

		Hpc.takeDamage(maxHp);
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp);
		assertEquals(maxHp, Hpc.getCurrentHealth());

		Hpc.takeDamage(maxHp * 2);
		assertEquals(0, Hpc.getCurrentHealth());
	}

	@Test
	void testTakeHeal() {
		HealthPointContainer Hpc = this.buildOne();
		int maxHp = Hpc.getMaxHealth();

		Hpc.takeDamage(maxHp);
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp * 2);
		assertEquals(maxHp, Hpc.getCurrentHealth());

		Hpc.takeDamage(maxHp * 2);
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp * 2, true);
		assertEquals(maxHp * 2, Hpc.getCurrentHealth());
	}

	@Test
	void testAddHitDie() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDie("1d6");
		assertEquals("3d6", Hpc.getAllCodes().get(0));

		assertTrue(Hpc.getMaxHealth() <= 18 && Hpc.getMaxHealth() > 12);
	}

	@Test
	void testAddHitDieMax() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDieMax("1d6");
		assertEquals("3d6", Hpc.getAllCodes().get(0));

		assertTrue(Hpc.getMaxHealth() == 18);
		Hpc.addHitDieMax("1d10");
		assertTrue(Hpc.getMaxHealth() == 28);
	}

	@Test
	void testGetCurrentHealthPercent() {
		HealthPointContainer Hpc = this.buildOne();

		assertEquals(100d, Hpc.getCurrentHealthPercent());

		Hpc.takeDamage(6);
		assertEquals(50d, Hpc.getCurrentHealthPercent());

		Hpc.takeDamage(6);
		assertEquals(0d, Hpc.getCurrentHealthPercent());
	}

	@Test
	void testEquals() {
		HealthPointContainer Hpc1 = this.buildOne();
		HealthPointContainer Hpc2 = this.buildOne();

		assertEquals(Hpc1, Hpc1);
		assertEquals(Hpc1, Hpc2);
		Hpc2.addHitDie("1d6");
		assertNotEquals(Hpc1, Hpc2);
	}

	@Test
	void testHashCode() {
		HealthPointContainer Hpc1 = this.buildOne();
		HealthPointContainer Hpc2 = this.buildOne();

		assertEquals(Hpc1.hashCode(), Hpc2.hashCode());
		assertEquals(Hpc1.hashCode(), Hpc1.hashCode());
		Hpc2.addHitDie("1d6");
		assertNotEquals(Hpc1.hashCode(), Hpc2.hashCode());
	}

	@Test
	void testToString() {
		HealthPointContainer Hpc = this.buildOne();
		assertEquals("2d6", Hpc.toString());
		Hpc.addHitDie("1d8");
		assertEquals("2d6"+System.lineSeparator() +"1d8", Hpc.toString());
	}
}
