package entity.basic.actorBase.container;

import java.io.Serializable;
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
import entity.basic.actorBase.ClassedActorBase;
import entity.basic.actorBase.I_HasHp;

/**
 * This Class holds the logic and value of an Actors healthpoints.
 * @see ClassedActorBase
 * @author Dave
 *
 */
public class HealthPointContainer implements Serializable{

	/**The {@link I_HasHp} that is the Parent of this container*/
	private I_HasHp Parent;

	/**this list holds all used hitdie and the result of the throw*/
	private final ObservableList<Pair<String, Integer>> HpList;
	/**
	 * exposes the hitdie List
	 * @return the {@link #HpList} of this object
	 */
	public ObservableList<Pair<String, Integer>> getHpList() { return this.HpList; }


	/**This Property holds the maximum Health value*/
	private final IntegerProperty maximumHealth;
	/**
	 * This Method exposes the {@link #maximumHealth} property
	 * @return the maximumHealth IntProperty
	 */
	public IntegerProperty maxHealthProperty() { return this.maximumHealth; }
	/**
	 * This method is used to access the value of the {@link #maximumHealth} property
	 * @return integer containing the maximum Health
	 */
	public int getMaxHealth() { return this.maximumHealth.get(); }


	/**This property holds the current health a character currently has*/
	private final IntegerProperty currentHealth;
	public IntegerProperty currentHealthProperty() { return this.currentHealth; }
	public int getCurrentHealth() { return this.currentHealth.get(); }
	public void takeDamage(int dmg) {
		int newHealth = this.getCurrentHealth() - dmg;
		this.currentHealth.set(newHealth);
	}

	private final DoubleProperty currentHealthPercent;
	public DoubleProperty currentHealthPercentProperty() { return this.currentHealthPercent; }
	public double getCurrentHealthPercent() { return this.currentHealthPercent.get(); }


	public HealthPointContainer(I_HasHp Parent) {
		super();

		this.Parent = Parent;

		this.HpList = FXCollections.observableArrayList();
		this.maximumHealth = new SimpleIntegerProperty(0);

		// CurrentHealthChangeListener which will update the parent of this container according to the changes
		this.currentHealth = new SimpleIntegerProperty(0);
		this.currentHealth.addListener((ov, oldVal, newVal) -> {
			//negative cap current health at 0
			if(newVal.intValue() < 0) {
				this.currentHealth.set(0);
				return;
			}

			final int correctedNewVal = newVal.intValue() < 0 ? 0 : newVal.intValue();

			if(correctedNewVal == 0)
				this.Parent.onHealthZero();
			else if(oldVal.intValue() == 0 && correctedNewVal > 0)
				this.Parent.onHealthNoLongerZero();
			else if(correctedNewVal == this.getMaxHealth())
				this.Parent.onHealthFull();

			this.Parent.onHealthGeneralChange();
		});


		// bind currentHealthPercent to currenthealth / max health * 100
		this.currentHealthPercent = new SimpleDoubleProperty();

		this.currentHealthPercent.bind(
			this.currentHealth.divide(
				this.maximumHealth
			).multiply(100));

		//implementing listener to hitdielist to update max and current hit die on a new hitdie
		ListChangeListener<Pair<String, Integer>> listener =
			c -> {
				final int hpGained =
					c.getAddedSubList().stream()
						.mapToInt(p -> p.getValue())
						.sum();

				int newMaxhp = this.getMaxHealth() + hpGained;
				this.maximumHealth.set(newMaxhp);

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
