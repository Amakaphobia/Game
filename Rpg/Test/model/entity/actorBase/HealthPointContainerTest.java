package model.entity.actorBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import javafx.collections.ObservableList;

import org.junit.jupiter.api.Test;

import boxes.Pair;
import common.damage.Damage;
import dicemachine.DiceCodeBase;
import entity.actorBase.container.HealthPointContainer;
import entity.actorBase.container.I_HasHp;
import entity.basic.common.enums.DamageType;

@SuppressWarnings({"javadoc"})
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
	void testGetMaxHealthPriv() {
		HealthPointContainer Hpc = this.buildOne();
		assertEquals(12, Hpc.getMaxHealth());
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		assertTrue(Hpc.getMaxHealth() <= 18 && Hpc.getMaxHealth() > 12);
	}

	@Test
	void testGetCurrentHealthPriv() {
		HealthPointContainer Hpc = this.buildOne();

		int testHp = Hpc.getMaxHealth();

		assertEquals(testHp, Hpc.getCurrentHealth());

		Damage odmg = new Damage(DiceCodeBase.flat(6), DamageType.PHSICAL_BLUDGEONING, false);
		testHp -= odmg.getDamage();
		Hpc.takeDamage(Arrays.asList(odmg)); //TODO test
		assertEquals(testHp, Hpc.getCurrentHealth());

		testHp += odmg.getDamage();
		Hpc.takeHeal(odmg.getDamage());
		assertEquals(testHp, Hpc.getCurrentHealth());
	}

	@Test
	void testTakeDamage() {
		HealthPointContainer Hpc = this.buildOne();
		int maxHp = Hpc.getMaxHealth();
		Damage odmg = new Damage(DiceCodeBase.flat(maxHp), DamageType.PHSICAL_BLUDGEONING, false);

		Hpc.takeDamage(Arrays.asList(odmg));
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp);
		assertEquals(maxHp, Hpc.getCurrentHealth());

		odmg = new Damage(DiceCodeBase.flat(maxHp * 2), DamageType.PHSICAL_BLUDGEONING, false);
		Hpc.takeDamage(Arrays.asList(odmg));
		assertEquals(0, Hpc.getCurrentHealth());
	}

	@Test
	void testTakeHeal() {
		HealthPointContainer Hpc = this.buildOne();
		int maxHp = Hpc.getMaxHealth();

		Damage odmg = new Damage(DiceCodeBase.flat(maxHp), DamageType.PHSICAL_BLUDGEONING, false);

		Hpc.takeDamage(odmg);
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp * 2);
		assertEquals(maxHp, Hpc.getCurrentHealth());

		odmg = new Damage(DiceCodeBase.flat(maxHp * 2), DamageType.PHSICAL_BLUDGEONING, false);
		Hpc.takeDamage(odmg);
		assertEquals(0, Hpc.getCurrentHealth());

		Hpc.takeHeal(maxHp * 2, true);
		assertEquals(maxHp * 2, Hpc.getCurrentHealth());
	}

	@Test
	void testAddHitDie() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDie(DiceCodeBase.roll(1, 6));
		assertEquals("3d6", Hpc.getAllCodes().get(0).toString());

		assertTrue(Hpc.getMaxHealth() <= 18 && Hpc.getMaxHealth() > 12);
	}

	@Test
	void testAddHitDieMax() {
		HealthPointContainer Hpc = this.buildOne();
		Hpc.addHitDieMax(DiceCodeBase.roll(1, 6));
		assertEquals("3d6", Hpc.getAllCodes().get(0).toString());

		assertTrue(Hpc.getMaxHealth() == 18);
		Hpc.addHitDieMax(DiceCodeBase.roll(1, 10));
		assertTrue(Hpc.getMaxHealth() == 28);
	}

	@Test
	void testGetCurrentHealthPercent() {

		I_HasHp Parent = new I_HasHp() {
			@Override public void onHealthZero() {}
			@Override public void onHealthNoLongerZero() {}
		};

		HealthPointContainer Hpc = new HealthPointContainer(Parent);

		Hpc.addHitDie(DiceCodeBase.flat(6));
		Hpc.addHitDie(DiceCodeBase.flat(6));

		assertEquals(100d, Hpc.getCurrentHealthPercent());

		Damage odmg = new Damage(DiceCodeBase.flat(6), DamageType.PHSICAL_BLUDGEONING, false);
		Hpc.takeDamage(odmg);
		assertEquals(50d, Hpc.getCurrentHealthPercent());

		Hpc.takeDamage(odmg);
		assertEquals(0d, Hpc.getCurrentHealthPercent());
	}

	@Test
	void testEquals() {
		HealthPointContainer Hpc1 = this.buildOne();
		HealthPointContainer Hpc2 = this.buildOne();

		assertEquals(Hpc1, Hpc1);
		assertEquals(Hpc1, Hpc2);
		Hpc1.addHitDie(DiceCodeBase.roll(1, 6));
		assertNotEquals(Hpc1, Hpc2);
	}

	@Test
	void testHashCode() {
		HealthPointContainer Hpc1 = this.buildOne();
		HealthPointContainer Hpc2 = this.buildOne();

		assertEquals(Hpc1.hashCode(), Hpc2.hashCode());
		assertEquals(Hpc1.hashCode(), Hpc1.hashCode());
		Hpc1.addHitDie(DiceCodeBase.roll(1, 6));
		assertNotEquals(Hpc1.hashCode(), Hpc2.hashCode());
	}

	@Test
	void testToString() {
		HealthPointContainer Hpc = this.buildOne();
		assertEquals("2d6", Hpc.toString());
		Hpc.addHitDie(DiceCodeBase.roll(1, 8));
		assertEquals("2d6"+System.lineSeparator() +"1d8", Hpc.toString());
	}
}
