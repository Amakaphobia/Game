package entity.actorBase.container;

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
import entity.actorBase.ClassedActorBase;

/**
 * This Class holds the logic and value of an Actors healthPoints.
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
	/**
	 * This Method is used to expose the currentHealth property
	 * @return IntegerProperty currentHealth
	 */
	public IntegerProperty currentHealthProperty() { return this.currentHealth; }
	/**
	 * this Method is used to access the value of the currentHealth property
	 * @return integer containing the currentHEalth of the parent
	 */
	public int getCurrentHealth() { return this.currentHealth.get(); }


	/**
	 * This Property holds the current Health in relation to the max health<br>
	 * <code>
	 * currentHealthPercent = currentHealth / MaxHealth * 100
	 * </code>
	 */
	private final DoubleProperty currentHealthPercent;
	/**
	 * this Method is used to expose the currentHealthPercent Property.
	 * @return the DoubleProperty currentHealth
	 */
	public DoubleProperty currentHealthPercentProperty() { return this.currentHealthPercent; }
	/**
	 * this Method is used to access the value of the currentHealthPercent property
	 * @return double between 0 and 100
	 */
	public double getCurrentHealthPercent() { return this.currentHealthPercent.get(); }

	/**
	 * this method is used to after damage computation to subtract the computed damage from the current health
	 * @param damage the total amount of damage taken
	 */
	public void takeDamage(int damage) {
		int newHealth = this.getCurrentHealth() - damage;
		this.currentHealth.set(newHealth);
	}

	/**
	 * this method is used to after heal computation to add the computed heal to the current health
	 * @param heal the total amount of heal received
	 */
	public void takeHeal(int heal) {
		int newHealth = this.getCurrentHealth() + heal;
		this.currentHealth.set(newHealth);
	}

	/** Constructor
	 * @param Parent the Actor that is the owner of this HealthContainer
	 */
	public HealthPointContainer(I_HasHp Parent) {
		super();

		this.Parent = Parent;

		this.HpList = FXCollections.observableArrayList();
		this.maximumHealth = new SimpleIntegerProperty(1);

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

	/**
	 * This Method is used to add a new HitDie to this HealthContainer
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDie(String diceCode) {
		I_DiceMachine Dm = new DiceMachine();
		int erg = Dm.getRoll(diceCode);
		this.HpList.add(new Pair<>(diceCode, erg));
	}

	/**
	 * This Method is used to add a new hitdie and force the maximum result
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDieMax(String diceCode) {
		StringTokenizer strk = new StringTokenizer(diceCode, "d");
		I_DiceMachine Dm = new DiceMachine();

		int multiplikator = 0;
		if(strk.hasMoreTokens())
			multiplikator = Dm.getRoll(strk.nextToken());

		int singleErg = 0;
		if(strk.hasMoreTokens())
			singleErg = Dm.getRoll(strk.nextToken());

		int erg = multiplikator * singleErg;

		this.HpList.add(new Pair<>(diceCode, erg));
	}
}
