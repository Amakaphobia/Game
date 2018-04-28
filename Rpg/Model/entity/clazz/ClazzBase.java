package entity.clazz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import common.attack.I_Attack;
import entity.actorBase.ClassedActorBase;
import entity.basic.common.enums.clazz.Clazzs;
import entity.basic.common.enums.skillsattributes.Skills;

/**
 * This is the base class for clazz implementations. it handles parent, name, id, leveling strategies, attacks and so on.
 * Its abstract method {@link #buildLevelStrategies()} needs to be overwritten to return a Map of level thresholds and
 * consumers for childclass for this class. on level up the class gets the requested consumer out of the map and
 * applies itself to it.
 *
 * @author Dave
 *
 * @see Serializable
 * @see ClassedActorBase
 * @see Skills
 * @see I_Attack
 *
 */
public abstract class ClazzBase implements Serializable{

	// Parent Handling

	/**the parent of this class*/
	protected ClassedActorBase Parent;
	/**@param Parent the parent you want to register*/
	public void registerEntity(ClassedActorBase Parent) { this.Parent = Parent; }
	/**Method used to unregister a parent*/
	public void unregisterEntity() { this.Parent = null; }

	//hit die

	/**the hitdie code this class gets on level up*/
	private final String hitDieCode;
	/**@return the hitdieCOde*/
	public String getHitDieCode() { return this.hitDieCode; }

	//leveling

	/**
	 * this map holds all leveling strategies. those are consumers for clazzBase implementations that actually perform
	 * the changes on level-up
	 */
	private final Map<Integer, Consumer<? super ClazzBase>> levelStrategies;
	/**
	 * This abstract Method is used internally to generate the {@link #levelStrategies} of this object.
	 * @return a map containing the levelStrategies keyed to their respective level
	 */
	protected abstract Map<Integer, Consumer<? super ClazzBase>> buildLevelStrategies();
	/**the current level of this class*/
	private int level;
	/**@return the current level*/
	public int getLevel() { return this.level; }
	/**use this method to level the class one level up*/
	public void levelUp(){
		this.level++;

		this.levelStrategies
			.getOrDefault(
				this.level,
				e -> {})
			.accept(this);

		if(this.Parent != null)
			this.Parent.onClassLevelUp(this);
	}

	// BaseAttacks

	/**this list holds all base attacks this class has*/
	private final List<I_Attack> baseAttacks;
	/**
	 * This method is used to add a new base attack to this class
	 * @param BaseAttack the base attack you want to add
	 */
	public void addBaseAttacks(I_Attack BaseAttack) { this.baseAttacks.add(BaseAttack); }
	/**
	 * Gets the Base Attack Bonus of a given Attack index (Starting at 0)
	 * @param index the index of the attack you want to pull
	 * @return the searched Base Attack Bonus
	 */
	public int getBaseAttackBonus(int index) {
		return this.baseAttacks.get(index).getBaseAttackBonus();
	}

	// SavingThrowModifier

	/**the modifier used for fortitude saves*/
	private int fortitudeSaveModifier;
	/**the modifier used for reflex saves*/
	private int reflexSaveModifier;
	/**the modifier used for will saves*/
	private int willSaveModifer;

	/**@return the fortitudeSaveModifier*/
	public final int getFortitudeSaveModifier() { return this.fortitudeSaveModifier; }
	/**@param fortitudeSaveModifier the fortitudeSaveModifier to set*/
	protected final void setFortitudeSaveModifier(int fortitudeSaveModifier) {
		this.fortitudeSaveModifier = fortitudeSaveModifier;
	}
	/**@return the reflexSaveModifier*/
	public final int getReflexSaveModifier() { return this.reflexSaveModifier; }
	/**@param reflexSaveModifier the reflexSaveModifier to set*/
	protected final void setReflexSaveModifier(int reflexSaveModifier) {
		this.reflexSaveModifier = reflexSaveModifier;
	}
	/**@return the willSaveModifer*/
	public final int getWillSaveModifer() { return this.willSaveModifer; }
	/**@param willSaveModifer the willSaveModifer to set*/
	protected final void setWillSaveModifer(int willSaveModifer) {
		this.willSaveModifer = willSaveModifer;
	}


	//name + id

	/**the name of the class*/
	protected final StringProperty name;
	/**@return the name Property*/
	public StringProperty nameProperty() { return this.name; }
	/**@return the name of this class*/
	public String getName() { return this.name.get(); }

	/**class identifier*/
	private final Clazzs id;
	/**@return the id*/
	public final Clazzs getId() { return this.id; }

	//class skills

	/**the class skills of this class*/
	private final List<Skills> classSkills;
	/**
	 * used to access the class skillls list
	 * @return the {@link #classSkills} object
	 */
	public List<Skills> getClassSkills() { return this.classSkills; }

	//Constructor

	/**
	 * Constructor {@link #ClazzBase(Clazzs)} also sets parent
	 * @param id the {@link Clazzs} id of the this class
	 * @param Parent the {@link ClassedActorBase} Parent object of this class
	 */
	public ClazzBase(Clazzs id, ClassedActorBase Parent) {
		this(id);
		this.Parent = Parent;
	}

	/**
	 * Constructor
	 * @param id the {@link Clazzs} id of the this class
	 */
	public ClazzBase(Clazzs id) {
		this.name = new SimpleStringProperty(id.getId());
		this.hitDieCode = id.getHitDieCode();
		this.classSkills = id.getClassSkills();
		this.id = id;

		this.levelStrategies = this.buildLevelStrategies();

		this.fortitudeSaveModifier = 0;
		this.reflexSaveModifier = 0;
		this.willSaveModifer = 0;

		this.baseAttacks = new ArrayList<>();

		// last steps - lets keep it that way
		this.level = 0;
		this.levelUp();
	}
}
