package model.entity.actorBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.collections.ObservableList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import boxes.Pair;
import dicemachine.DiceCodeBase;
import entity.actorBase.container.HealthPointContainer;
import entity.actorBase.container.I_HasHp;

@SuppressWarnings({"deprecation", "javadoc"})
class HealthPointContainerTest {

	public HealthPointContainer buildOne() {
		I_HasHp Parent = new I_HasHp() {
			@Override public void onHealthZero() {}
			@Override public void onHealthNoLongerZero() {}
		};

		HealthPointContainer hpc = new HealthPointContainer(Parent);

		hpc.addHitDieMax(DiceCodeBase.roll(1, 6));
		hpc.addHitDieMax(DiceCodeBase.roll(1, 6));

		return hpc;
	}

	@Test
	@Disabled
	void testGetHpList() {
		ObservableList<?> ll = this.buildOne().getHpList();
		assertEquals(2, ll.size());
		assertEquals("1d6", ((Pair<?,?>)ll.get(0)).getKey().toString() );
		assertEquals(6, ((Pair<?,?>)ll.get(0)).getValue() );
	}

	@Test
	void testGetAllCodes() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		Hpc.addHitDie(DiceCodeBase.roll(1, 8));
		Hpc.addHitDie(DiceCodeBase.roll(2, 8));

		assertEquals(Hpc.getAllCodes().get(0).toString(), "6d6");
		assertEquals(Hpc.getAllCodes().get(1).toString(), "3d8");
	}

	@Test
	@Disabled
	void testGetMaxHealthPriv() {
		HealthPointContainer Hpc = this.buildOne();
		assertEquals(12, Hpc.getMaxHealthPriv());
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		assertTrue(Hpc.getMaxHealthPriv() <= 18 && Hpc.getMaxHealthPriv() > 12);
	}

	@Test
	@Disabled
	void testGetCurrentHealthPriv() {
		HealthPointContainer Hpc = this.buildOne();

		int testHp = Hpc.getMaxHealthPriv();

		assertEquals(testHp, Hpc.getCurrentHealth());

		int dmg = 6;
		testHp -= dmg;
		Hpc.takeDamage(dmg); //TODO test
		assertEquals(testHp, Hpc.getCurrentHealth());

		testHp += dmg;
		Hpc.takeHeal(dmg);
		assertEquals(testHp, Hpc.getCurrentHealth());
	}

	@Test
	@Disabled
	void testTakeDamage() {
		HealthPointContainer Hpc = this.buildOne();
		int maxHp = Hpc.getMaxHealthPriv();

		Hpc.takeDamage(maxHp);
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp);
		assertEquals(maxHp, Hpc.getCurrentHealth());

		Hpc.takeDamage(maxHp * 2);
		assertEquals(0, Hpc.getCurrentHealth());
	}

	@Test
	@Disabled
	void testTakeHeal() {
		HealthPointContainer Hpc = this.buildOne();
		int maxHp = Hpc.getMaxHealthPriv();

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
	@Disabled
	void testAddHitDie() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		assertEquals("3d6", Hpc.getAllCodes().get(0).toString());

		assertTrue(Hpc.getMaxHealthPriv() <= 18 && Hpc.getMaxHealthPriv() > 12);
	}

	@Test
	@Disabled
	void testAddHitDieMax() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDieMax(DiceCodeBase.roll(1, 6));
		assertEquals("3d6", Hpc.getAllCodes().get(0).toString());

		assertTrue(Hpc.getMaxHealthPriv() == 18);
		Hpc.addHitDieMax(DiceCodeBase.roll(1, 10));
		assertTrue(Hpc.getMaxHealthPriv() == 28);
	}


	@Test
	@Disabled
	void testGetCurrentHealthPercent() {
		HealthPointContainer Hpc = this.buildOne();

		assertEquals(100d, Hpc.getCurrentHealthPercent());

		Hpc.takeDamage(6);
		assertEquals(50d, Hpc.getCurrentHealthPercent());

		Hpc.takeDamage(6);
		assertEquals(0d, Hpc.getCurrentHealthPercent());
	}

	@Test
	@Disabled
	void testEquals() {
		HealthPointContainer Hpc1 = this.buildOne();
		HealthPointContainer Hpc2 = this.buildOne();

		assertEquals(Hpc1, Hpc1);
		assertEquals(Hpc1, Hpc2);
		Hpc1.addHitDie(DiceCodeBase.roll(1, 6));
		assertNotEquals(Hpc1, Hpc2);
	}

	@Test
	@Disabled
	void testHashCode() {
		HealthPointContainer Hpc1 = this.buildOne();
		HealthPointContainer Hpc2 = this.buildOne();

		assertEquals(Hpc1.hashCode(), Hpc2.hashCode());
		assertEquals(Hpc1.hashCode(), Hpc1.hashCode());
		Hpc1.addHitDie(DiceCodeBase.roll(1, 6));
		assertNotEquals(Hpc1.hashCode(), Hpc2.hashCode());
	}

	@Test
	@Disabled
	void testToString() {
		HealthPointContainer Hpc = this.buildOne();
		assertEquals("2d6", Hpc.toString());
		Hpc.addHitDie(DiceCodeBase.roll(1, 8));
		assertEquals("2d6"+System.lineSeparator() +"1d8", Hpc.toString());
	}
}
