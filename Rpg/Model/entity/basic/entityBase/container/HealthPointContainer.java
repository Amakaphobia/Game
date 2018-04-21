package entity.basic.entityBase.container;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import boxes.Pair;
import dicemachine.DiceMachine;
import dicemachine.I_DiceMachine;

@SuppressWarnings("javadoc")
public class HealthPointContainer implements Serializable{

	private final ObservableList<Pair<String, Integer>> HpList;
	public List<Pair<String, Integer>> getHpList() { return this.HpList; }

	private final IntegerProperty maxHealth;
	public IntegerProperty maxHealthProperty() { return this.maxHealth; }
	public int getMaxHealth() { return this.maxHealth.get(); }

	private final IntegerProperty currentHealth;
	public IntegerProperty currentHealthProperty() { return this.currentHealth; }
	public int getCurrentHealth() { return this.currentHealth.get(); }

	private final DoubleProperty currentHealthPercent;
	public final DoubleProperty currentHealthPercentProperty() { return this.currentHealthPercent; }
	public final double getCurrentHealthPercent() { return this.currentHealthPercent.get(); }


	public HealthPointContainer() {
		super();
		this.HpList = FXCollections.observableArrayList();
		this.maxHealth = new SimpleIntegerProperty(0);
		this.currentHealth = new SimpleIntegerProperty(0);
		this.currentHealthPercent = new SimpleDoubleProperty();

		// bind currentHealthPercent to currenthealth / max health * 100
		this.currentHealthPercent.bind(
			this.currentHealth.divide(
				this.maxHealth
			).multiply(100));

		ListChangeListener<Pair<String, Integer>> listener =
			c -> {
				final int hpGained =
					c.getAddedSubList().stream()
						.mapToInt(p -> p.getValue())
						.sum();

				int newMaxhp = this.getMaxHealth() + hpGained;
				this.maxHealth.set(newMaxhp);

				int newCurrentHp = this.getCurrentHealth() + hpGained;
				this.currentHealth.set(newCurrentHp);
			};

		this.HpList.addListener(listener);
	}

	public void addHitDie(String diceCode) {
		I_DiceMachine Dm = new DiceMachine();
		int erg = Dm.getRoll(diceCode);
		this.HpList.add(new Pair<>(diceCode, erg));
	}

	public void addHitDieMax(String diceCode) {
		StringTokenizer strk = new StringTokenizer(diceCode, "d");
		I_DiceMachine Dm = new DiceMachine();

		int multiplikator = 0;
		if(strk.hasMoreTokens())
			multiplikator = Dm.getRoll(strk.nextToken());

		int singleErg = 0;
		while(strk.hasMoreTokens())
			singleErg += Dm.getRoll(strk.nextToken());

		int erg = multiplikator * singleErg;

		this.HpList.add(new Pair<>(diceCode, erg));
	}
}
