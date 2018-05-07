package entity.actorBase.container;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import boxes.Pair;
import common.damage.Damage;
import dicemachine.DiceCodeBase;
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

	//total Maximum Health
	/**This Property holds the total maximum Health value of this level*/
	private final IntegerProperty totalMaximumHealth = new SimpleIntegerProperty(1);
	/**
	 * This Method exposes the {@link #totalMaximumHealth} property
	 * @return the totalMaximumHealth IntProperty
	 */
	public final IntegerProperty totalMaximumHealthProperty() { return this.totalMaximumHealth; }
	/**
	 * This method is used to access the value of the {@link #totalMaximumHealth} property
	 * @return integer containing the total maximum Health
	 */
	public final int getTotalMaximumHealth() { return this.maximumHealth.get(); }
	/**bind currentHealthPercent to current health / max health * 100*/
	private final ChangeListener<Number> MaximumPercentChangeListener =
		(ov, newVal, oldVal) -> this.currentHealthPercent.set(this.getCurrentHealth() / newVal.doubleValue() * 100d);


	//total Current Health
	/**This property holds the total current health a character currently has*/
	private final IntegerProperty totalCurrentHealth = new SimpleIntegerProperty(0);
	/**
	 * This Method is used to expose the totalCurrentHealth property
	 * @return IntegerProperty total currentHealth
	 */
	public IntegerProperty totalCurrentHealthProperty() { return this.totalCurrentHealth; }
	/**
	 * this Method is used to access the value of the total currentHealth property
	 * @return integer containing the total currentHealth of the parent
	 */
	public int getTotalCurrentHealth() { return this.currentHealth.get(); }
	/**bind currentHealthPercent to current health / max health * 100*/
	private final ChangeListener<Number> CurrentPercentChangeListener =
		(ov, newVal, oldVal) -> this.currentHealthPercent.set(newVal.doubleValue()/this.getMaxHealth() * 100d);


	//Temporary Hp
	/**holds temporary hp*/
	private final IntegerProperty temporaryHp = new SimpleIntegerProperty(0);
	/**@return temporary hp as integer*/
	public final int getTemporaryHp() { return this.temporaryHp.get(); }
	/**@return {@link #temporaryHp}*/
	public final IntegerProperty temporaryHpProperty() { return this.temporaryHp; }
	/**this listener updates total health values when temp hp is changed*/
	private final ChangeListener<Number> TemporaryHpChangeListener =
			(ov, newVal, oldVal) -> {
				if(newVal.intValue() < 0) {
					this.temporaryHp.set(0);
					return;
				}
				this.totalMaximumHealth.set(this.getMaxHealth() + newVal.intValue());
				this.totalCurrentHealth.set(this.getCurrentHealth() + newVal.intValue());
			};

	//HPList
	/**this list holds all used hitdie and the result of the throw*/
	private final ObservableList<Pair<I_DiceCode, Integer>> HpList = FXCollections.observableArrayList();
	/**
	 * exposes the hitdie List
	 * @return the {@link #HpList} of this object
	 */
	public ObservableList<Pair<I_DiceCode, Integer>> getHpList() { return this.HpList; }
	/**this Listener is used by the hitdie list*/
	private final ListChangeListener<Pair<I_DiceCode, Integer>> HpListChangeListener =
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

				final int max = this.getMaxHealth() + hpChange - firstOne;
				this.maximumHealth.set(max);

				final int cur = this.getCurrentHealth() + hpChange;
				this.currentHealth.set(cur);

				final double per = (double)this.getTotalCurrentHealth() / (double) this.getMaxHealth() * 100d;
				this.currentHealthPercent.set(per);
			}
		};

	//Maximum Health
	/**This Property holds the maximum Health value of this level*/
	private final IntegerProperty maximumHealth = new SimpleIntegerProperty(1);
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
	/**this listener updates total max health on max health change*/
	private final ChangeListener<Number> MaximumHealthChangeListener =
		(ov, newVal, oldVal) -> {
			if(newVal.intValue() < 0) {
				this.maximumHealth.set(0);
				return;
			}
			this.totalMaximumHealth.set(newVal.intValue() + this.getTemporaryHp());
		};

	//Current Health
	/**This property holds the current health a character currently has*/
	private final IntegerProperty currentHealth = new SimpleIntegerProperty(0);
	/**
	 * This Method is used to expose the currentHealth property
	 * @return IntegerProperty currentHealth
	 */
	public IntegerProperty currentHealthProperty() { return this.currentHealth; }
	/**
	 * this Method is used to access the value of the currentHealth property
	 * @return integer containing the currentHealth of the parent
	 */
	public int getCurrentHealth() { return this.currentHealth.get(); }
	/**this listener caps current health on 0 updates the parent on change and percentile change*/
	private final ChangeListener<Number> CurrentHealthChangeListener =
			(ov, oldVal, newVal) -> {
				//negative cap current health at 0
				if(newVal.intValue() < 0) {
					this.currentHealth.set(0);
					return;
				}


				int totalCH = newVal.intValue() + this.getTemporaryHp();
				this.totalCurrentHealth.set(totalCH);
				this.currentHealthPercent.set((double)totalCH /this.getMaxHealth() * 100d);

				if(newVal.intValue() == 0)
					this.Parent.onHealthZero();
				else if(oldVal.intValue() == 0 && newVal.intValue() > 0)
					this.Parent.onHealthNoLongerZero();
				else if(newVal.intValue() == this.getMaxHealth())
					this.Parent.onHealthFull();

				this.Parent.onHealthGeneralChange();
			};

	//Current Health Percent
	/**
	 * This Property holds the current Health in relation to the max health<br>
	 * <code>
	 * currentHealthPercent = currentHealth / MaxHealth * 100
	 * </code>
	 */
	private final DoubleProperty currentHealthPercent = new SimpleDoubleProperty();
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

	//Resistance
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

	//DamageHandling
	/**
	 * This Method is used to apply damage to this object.
	 * @see Damage
	 * @param Damages a list of damage objects that this actor endures
	 */
	public void takeDamage(List<Damage> Damages) {
		this.takeDamage(
			Damages.stream()
				.filter(d -> !this.isImmunTo(d.getType()))
				.mapToInt(d ->
					this.isResistantTo(d.getType()) ?
							(int)Math.floor(d.getDamage() * .5) :
							d.getDamage())
				.sum());
	}

	/**
	 * This Method is used to apply damage to this object.
	 * @see Damage
	 * @param Damage the damage to take
	 */
	public void takeDamage(Damage Damage) {
		this.takeDamage(Arrays.asList(Damage));
	}

	/**
	 * This Method is used to add temporary hp from a {@link HealthPointContainer}. It uses the {@link #getTotalCurrentHealth()}
	 * as input.
	 * @see #addTemporaryHp(int)
	 * @param TempHp the container to add
	 */
	public void addTemporaryHp(HealthPointContainer TempHp) {
		this.addTemporaryHp(TempHp.getTotalCurrentHealth());
	}

	/**
	 * This Method is used to add Temporary hp from a {@link I_DiceCode} implementation. It uses the {@link DiceCodeBase#getValue()}
	 * as input.
	 * @see HealthPointContainer#addTemporaryHp(int)
	 * @param Dice the hitdie you want to add.
	 */
	public void addTemporaryHp(I_DiceCode Dice) {
		this.addTemporaryHp(Dice.getValue());
	}

	/**
	 * This method is used to add a flat integer value to the sum of temporary hitpoints. If the given value is less
	 * than or equal to zero it does nothing. If you want to subtract hp from the temporary hitpoints use {@link #takeDamage(Damage)}
	 * @param value the value you want to add
	 */
	public void addTemporaryHp(int value) {
		if(value <= 0) return;

		int currentval = this.getTemporaryHp();
		this.temporaryHp.set(currentval + value);
	}

	/**
	 * this method is used to after damage computation to subtract the computed damage from the current health
	 * @param damage the total amount of damage taken
	 */
	private void takeDamage(final int damage) {
		final int tmpHp = this.getTemporaryHp();
		if(tmpHp > 0) {
			if(tmpHp > damage)
				this.temporaryHp.set(tmpHp - damage);
			else {
				this.temporaryHp.set(0);
				this.takeDamage(damage - tmpHp);
			}

			return;
		}

		final int curHp = this.getCurrentHealth();
		this.currentHealth.set(curHp - damage);
	}

	/**
	 * this method is used after heal computation to add the computed heal to the current health
	 * @param heal the total amount of heal received
	 * @param canOverHeal set this to true if currentHealth can get bigger than max health
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

		this.HpList.addListener(this.HpListChangeListener);

		this.maximumHealth.addListener(this.MaximumHealthChangeListener);
		this.currentHealth.addListener(this.CurrentHealthChangeListener);
		this.totalCurrentHealth.addListener(this.CurrentPercentChangeListener);
		this.totalMaximumHealth.addListener(this.MaximumPercentChangeListener);
		this.temporaryHp.addListener(this.TemporaryHpChangeListener);

		this.resistance = new HashSet<>();
		this.immunity = new HashSet<>();
	}

	/**
	 * This method is used to generate a compressed list of all diceCodes in this Objects {@link #HpList}
	 * @return List Containing I_DiceCodes like: <br>
	 * "xdy(+z)" grouped by the part after x<br>
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
					}

					counted.addDecorator(current.getDecorator());

					return counted;
				})
				.collect(toList());
	}

	/**
	 * This Method is used to add a new HitDie to this HealthContainer
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDie(I_DiceCode diceCode) {
		Pair<I_DiceCode, Integer> p =new Pair<>(diceCode, diceCode.getValue());
		this.HpList.add(p);
	}

	/**
	 * This Method is used to add a new hitdie and force the maximum result
	 * @param diceCode the dicecode of the new hitdie
	 */
	public void addHitDieMax(I_DiceCode diceCode) {
		this.HpList.add(new Pair<>(diceCode, diceCode.maxValue()));
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
