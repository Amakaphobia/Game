package entity.actorBase.container;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import boxes.Pair;
import dicemachine.I_DiceCode;
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
	 * this method is used after heal computation to add the computed heal to the current health
	 * @param heal the total amount of heal received
	 * @param canOverHeal set this to true if currentHealth can get bigger than maxhealth
	 */
	public void takeHeal(int heal, boolean canOverHeal) {

		int newHealth;
		if(canOverHeal)
			newHealth = this.getCurrentHealth() + heal;
		else {
			int test = this.getCurrentHealth() + heal;
			newHealth = test > this.getMaxHealth() ? this.getMaxHealth() : test;
		}

		this.currentHealth.set(newHealth);
	}

	/**
	 * this method is used after heal computation to add the computed heal to the current health it calls
	 * {@link #takeHeal(int, boolean)} with heal, false
	 * @param heal the total amount of heal received
	 */
	public void takeHeal(int heal) {
		this.takeHeal(heal, false);
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

		this.currentHealth.addListener((ov, oldVal, newVal) -> {
			this.currentHealthPercent.set(newVal.doubleValue()/this.getMaxHealth() * 100d);
		});

		//implementing listener to hitdielist to update max and current hit die on a new hitdie
		ListChangeListener<Pair<String, Integer>> listener =
			c -> {
				while(c.next()) {
					if(c.wasPermutated() || c.wasUpdated()) continue;

					final int firstOne = this.HpList.size() == 1 ? 1 : 0;

					final int hpChange =
						c.getAddedSubList().stream()
							.mapToInt(Pair::getValue)
							.sum()
						-
						c.getRemoved().stream()
							.mapToInt(Pair::getValue)
							.sum();

					this.maximumHealth.set(this.getMaxHealth() + hpChange - firstOne);

					this.currentHealth.set(this.getCurrentHealth() + hpChange);
				}
			};

		this.HpList.addListener(listener);
	}

	/**
	 * This method is used to generate a compressed list of all diceCodes in this Objects {@link #HpList}
	 * @return List Containing strings like: <br>
	 * "xdy(+1)" grouped by the part after x<br>
	 * all x with the same key get added up so that a HpList containing the dice code 1d6 twice gets turned into a
	 * list with one Element (2d6)
	 */
	public List<String> getAllCodes(){
		return
			this.HpList.stream()
				.map(Pair::getKey)
				.collect(groupingBy(s -> s.substring(s.indexOf("d")+1) ))
				.entrySet().stream()
				.map(entry -> {
					int totalOfOneSize =
						entry.getValue().stream()
							.map(dC -> dC.substring(0, dC.indexOf("d")))
							.mapToInt(Integer::valueOf)
							.sum();

					return String.format("%sd%s", totalOfOneSize, entry.getKey());
				})
				.collect(toList());
	}


	/**
	 * This Method is used to add a new HitDie to this HealthContainer
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDie(I_DiceCode diceCode) {
		this.HpList.add(new Pair<>(diceCode.toString(), diceCode.getValue()));
	}


	/**
	 * This Method is used to add a new hitdie and force the maximum result
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDieMax(I_DiceCode diceCode) {
		this.HpList.add(new Pair<>(diceCode.toString(), diceCode.max()));
	}

	//obj-methods

	@Override
	public String toString() {
		return this.getAllCodes().stream()
					.collect(joining(System.lineSeparator()));
	}


	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof HealthPointContainer)) return false;

		HealthPointContainer other = (HealthPointContainer) obj;

		return this.HpList.equals(other.HpList);
	}

	@Override
	public int hashCode() {
		return this.HpList.stream()
				.map(Pair::getKey)
				.mapToInt(String::hashCode)
				.sum();
	}
}
