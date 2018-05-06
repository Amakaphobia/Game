package entity.actorBase.container;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import boxes.Pair;
import common.damage.Damage;
import dicemachine.DiceCodeBase;
import dicemachine.DiceCodeFlat;
import dicemachine.DiceCodeRoll;
import dicemachine.I_DiceCode;
import entity.actorBase.ClassedActorBase;
import entity.basic.common.enums.DamageType;

/**
 * This Class holds the logic and value of an Actors healthPoints.
 * @see ClassedActorBase
 * @author Dave
 *
 */
public class HealthPointContainer implements Serializable{

	//TODO test with temp hp

	/**The {@link I_HasHp} that is the Parent of this container*/
	private I_HasHp Parent;

	//Outside

	private final IntegerProperty maximumHealthOut = new SimpleIntegerProperty();
	/**This Property holds the maximum Health value*/
	private final IntegerProperty maximumHealth = new SimpleIntegerProperty(1);;
	/**
	 * This Method exposes the {@link #maximumHealth} property
	 * @return the maximumHealth IntProperty
	 */
	public IntegerProperty maxHealthPrivProperty() { return this.maximumHealth; }
	/**
	 * This method is used to access the value of the {@link #maximumHealth} property
	 * @return integer containing the maximum Health
	 */
	public int getMaxHealthPriv() { return this.maximumHealth.get(); }

	public int getMaxHealthOut() { return this.maximumHealthOut.get(); }

	public IntegerProperty maxHealthOutProperty() { return this.maximumHealthOut; }




	private final IntegerProperty currentHealthOut = new SimpleIntegerProperty();
	private final DoubleProperty currentHealthPercentOut = new SimpleDoubleProperty();

	private final ObjectProperty<HealthPointContainer> tempHp;
	public HealthPointContainer getTempHp() { return this.tempHp.get(); }
	public ObjectProperty<HealthPointContainer> tempHpProperty() { return this.tempHp; }
	public void setTempHp(HealthPointContainer TempHp) { this.tempHp.set(TempHp); }

	/**this list holds all used hitdie and the result of the throw*/
	private final ObservableList<Pair<I_DiceCode, Integer>> HpList = FXCollections.observableArrayList();
	/**
	 * exposes the hitdie List
	 * @return the {@link #HpList} of this object
	 */
	public ObservableList<Pair<I_DiceCode, Integer>> getHpList() { return this.HpList; }




	/**This property holds the current health a character currently has*/
	private final IntegerProperty currentHealth = new SimpleIntegerProperty(0);;
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
	private final DoubleProperty currentHealthPercent = new SimpleDoubleProperty();;
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

	/**holds all resistances. resistances against damage types half the damage taken*/
	private final Set<DamageType> resistance;
	/**@return the resistance set*/
	public Set<DamageType> getResistance() { return this.resistance; }
	/**@param type the resistance to add*/
	public void addResistance(DamageType type) { this.resistance.add(type); }
	/**@param type the resistance to remove*/
	public void removeResistance(DamageType type) { this.resistance.remove(type); }
	/**
	 * this method is used to check if this actor is resistant to a given type
	 * @param type the type you want to test
	 * @return true if resistant false if not
	 */
	public boolean isResistantTo(DamageType type) {
		return this.resistance.stream()
					.filter(dt -> type.equals(dt))
					.findFirst()
					.isPresent();
	}

	/**holds all immunities. immunities against damage types null the damage taken*/
	private final Set<DamageType> immunity;
	/**@return the immunity set*/
	public Set<DamageType> getImmunity() { return this.immunity; }
	/**@param type the immunity you want to add*/
	public void addImmunity(DamageType type) { this.immunity.add(type); }
	/**@param type the immunity you want to remove*/
	public void removeImmunity(DamageType type) { this.immunity.remove(type); }
	/**
	 * this method is used to check if this actor is immune to a given type
	 * @param type the type you want to test
	 * @return true if immune false if not
	 */
	public boolean isImmunTo(DamageType type) {
		return this.immunity.stream()
					.filter(dt -> type.equals(dt))
					.findFirst()
					.isPresent();
	}

	/**
	 * This method is used to take damage.
	 * @param damage a list of damage objects that this actor endures
	 */
	public void takeDamage(List<Damage> damage) {
		final int damageTaken = damage.stream()
			.filter(d -> !this.isImmunTo(d.getType()))
			.mapToInt(d ->
				this.isResistantTo(d.getType()) ?
						(int)Math.floor(d.getDamage() * .5) :
						d.getDamage())
			.sum();

		if(this.getTempHp() != null) {
			int dmgOverflow = damageTaken - this.getTempHp().getCurrentHealth();
			if(dmgOverflow >= 0) {
				this.tempHp.set(null);
				this.takeDamage(dmgOverflow);
			} else
				this.getTempHp().takeDamage(damageTaken);
		}
	}



	public void addTemporaryHp(HealthPointContainer TempHp) {
		if(this.getTempHp() == null)
			this.tempHp.set(TempHp);
		else {
			int currentTempHp = this.getTempHp().getCurrentHealth();
			TempHp.addHitDie(DiceCodeBase.flat(currentTempHp));
			this.tempHp.set(TempHp);
		}



	}

	/**
	 * this method is used to after damage computation to subtract the computed damage from the current health
	 * @param damage the total amount of damage taken
	 */
	@Deprecated //TODO morph private remove tag
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
			newHealth = test > this.getMaxHealthPriv() ? this.getMaxHealthPriv() : test;
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


	private ListChangeListener<Pair<I_DiceCode, Integer>> HpListChangeListener=
		c -> {
			while(c.next()) {
				if(c.wasPermutated() || c.wasUpdated()) continue;

				final int firstOne = this.HpList.size() == 1 ? 1 : 0;

				final int hpChange =
					c.getAddedSubList().stream()
						.mapToInt(p -> p.getValue())
						.sum()
					-
					c.getRemoved().stream()
						.mapToInt(p -> p.getValue())
						.sum();

				this.maximumHealth.set(this.getMaxHealthPriv() + hpChange - firstOne);

				this.currentHealth.set(this.getCurrentHealth() + hpChange);
			}
		};

	private ChangeListener<HealthPointContainer> tempHpChange =
		(ov, oldval, newval) -> {
			this.currentHealthOut.unbind();
			this.currentHealthPercentOut.unbind();
			this.maximumHealthOut.unbind();

			if(newval == null) {
				this.currentHealthOut.bind(this.currentHealth);
				this.maximumHealthOut.bind(this.maximumHealth);
				this.currentHealthPercentOut.bind(this.currentHealthPercent);
			}else {
				this.currentHealthOut.bind(this.currentHealth.add(this.getTempHp().currentHealthOut));
				this.maximumHealthOut.bind(this.maximumHealth.add(this.getTempHp().maximumHealthOut));
				this.currentHealthPercentOut.bind(
					(this.currentHealthPercent
						.add(this.getTempHp().currentHealthPercentOut))
						.divide(2));
			}
		};

	/** Constructor
	 * @param Parent the Actor that is the owner of this HealthContainer
	 */
	public HealthPointContainer(I_HasHp Parent) {
		super();

		this.Parent = Parent;

		// CurrentHealthChangeListener which will update the parent of this container according to the changes
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
			else if(correctedNewVal == this.getMaxHealthPriv())
				this.Parent.onHealthFull();

			this.Parent.onHealthGeneralChange();
		});


		// bind currentHealthPercent to currenthealth / max health * 100
		this.currentHealth.addListener((ov, oldVal, newVal) -> {
			this.currentHealthPercent.set(newVal.doubleValue()/this.getMaxHealthPriv() * 100d);
		});

		//implementing listener to hitdielist to update max and current hit die on a new hitdie


		this.HpList.addListener(HpListChangeListener);

		//Temp HP

		this.tempHp = new SimpleObjectProperty<>();
		this.tempHp.addListener(tempHpChange);

		this.resistance = new HashSet<>();
		this.immunity = new HashSet<>();
	}

	/**
	 * This method is used to generate a compressed list of all diceCodes in this Objects {@link #HpList}
	 * @return List Containing strings like: <br>
	 * "xdy(+1)" grouped by the part after x<br>
	 * all x with the same key get added up so that a HpList containing the dice code 1d6 twice gets turned into a
	 * list with one Element (2d6)
	 */
	public List<I_DiceCode> getAllCodes(){
		return
			this.HpList.stream()
				.map(Pair::getKey)
				.collect(groupingBy(dc -> {
					String s = dc.toString();
					int index = s.indexOf("d");
					if(index == -1)
						return s;
					else {
						String ret = s.substring(index +1);
						return ret;
					}
				}))
				.entrySet().stream()
				.map(entry -> {
					I_DiceCode current = entry.getValue().get(0);
					DiceCodeBase counted = null;

					if(current instanceof DiceCodeRoll) {
						DiceCodeRoll currentRoll = (DiceCodeRoll) current;
						int countedDice = 0;
						for(I_DiceCode e : entry.getValue())
							countedDice += ((DiceCodeRoll)e).getDiceCount();
						counted = (DiceCodeBase) DiceCodeBase.roll(countedDice, currentRoll.getDiceSize());
					}else if(current instanceof DiceCodeFlat) {
						DiceCodeFlat currentFlat = (DiceCodeFlat)current;
						counted = (DiceCodeBase) DiceCodeBase.flat(currentFlat.getFlat() * entry.getValue().size());
					}

					counted.addDecorator(current.getDecorator());

					return counted;
				})
				.collect(toList());
	}

	//TODO addTempHp(hitdie)

	/**
	 * This Method is used to add a new HitDie to this HealthContainer
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDie(I_DiceCode diceCode) {
		this.HpList.add(new Pair<>(diceCode, diceCode.getValue()));
	}


	/**
	 * This Method is used to add a new hitdie and force the maximum result
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDieMax(I_DiceCode diceCode) {
		this.HpList.add(new Pair<>(diceCode, diceCode.max()));
	}

	//obj-methods

	@Override
	public String toString() {
		return this.getAllCodes().stream()
					.map(dc -> dc.toString())
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
				.mapToInt(I_DiceCode::hashCode)
				.sum();
	}
}
