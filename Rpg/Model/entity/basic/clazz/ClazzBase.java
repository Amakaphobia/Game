package entity.basic.clazz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import common.attack.I_Attack;
import common.decorator.SimpleIntegerDecorator;
import common.render.I_InfoAble;
import entity.actorBase.ClassedActorBase;
import entity.basic.common.enums.clazz.Clazzs;
import entity.basic.common.enums.skillsattributes.Skills;
import entity.magic.I_Spell;
import entity.magic.I_SpellStrategy;
import entity.magic.SpellBase;

/**
 * This is the base class for clazz implementations. it handles parent, name, id, leveling strategies, attacks
 * and so on.<br>
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
 */
public abstract class ClazzBase implements I_InfoAble, Serializable{

	// TODO ClassTraits

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

		this.fortitudeSaveModifier = new SimpleIntegerDecorator(0);
		this.reflexSaveModifier = new SimpleIntegerDecorator(0);
		this.willSaveModifer = new SimpleIntegerDecorator(0);

		this.baseAttacks = new ArrayList<>();

		this.SpellStrategy = this.injectSpellStrategy();

		// last steps - lets keep it that way
		this.level = 0;
		this.levelUp();
	}

	// Parent Handling

	/**the parent of this class*/
	protected ClassedActorBase Parent;
	/**@param Parent the parent you want to register*/
	public void registerEntity(ClassedActorBase Parent) { this.Parent = Parent; }
	/**Method used to unregister a parent*/
	public void unregisterEntity() { this.Parent = null; }

	//level handling + hit die

	/**the hitdie code this class gets on level up*/
	private final String hitDieCode;
	/**@return the hitdie Code*/
	public String getHitDieCode() { return this.hitDieCode; }


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
		int baseAttackBonus;
		
		try {
			baseAttackBonus = this.baseAttacks.get(index).getBaseAttackBonus();
		}catch(IndexOutOfBoundsException  e) {
			baseAttackBonus = 0;
		}
		
		return baseAttackBonus;
	}
	/**
	 * This method gives a count of possible attacks er combat round.
	 * @return count of attacks
	 */
	public int getBaseAttackCount() {
		return this.baseAttacks.size();
	}

	// SpellStrategy

	/**holds the strategy for spell casting*/
	protected I_SpellStrategy SpellStrategy;
	/**
	 * This abstract Method is used internally during object construction in inject the right {@link I_SpellStrategy}
	 * for this class.
	 * @return a {@link I_SpellStrategy} implementation suitable for this class
	 */
	protected abstract I_SpellStrategy injectSpellStrategy();
	/**
	 * Delegator method for {@link I_SpellStrategy#isMagic()} of the {@link #SpellStrategy} object.
	 * @see I_SpellStrategy
	 * @return true if this class can cast spells, false if not
	 */
	public boolean isMagical() { return this.SpellStrategy.isMagic(); }
	/**
	 * This method is used to access all Spells this class can cast in a Single List. it is a delegator for
	 * {@link I_SpellStrategy#getSpellsLearned()}.
	 *
	 * @return a List with all Spells this class has learned.
	 *
	 * @see List
	 * @see I_Spell
	 * @see I_SpellStrategy
	 */
	public List<SpellBase<?>> getSpellsLearned() { return this.SpellStrategy.getSpellsLearned(); }
	/**
	 * This method is used to access all Spells this class can learn in a Single List. it is a delegator for
	 * {@link I_SpellStrategy#getSpellsComplete()}.
	 *
	 * @return a List with all Spells this class can learn.
	 *
	 * @see List
	 * @see I_Spell
	 * @see I_SpellStrategy
	 */
	public List <SpellBase<?>> getSpellsComplete() { return this.SpellStrategy.getSpellsComplete(); }
	/**
	 * This method is used to test if a Spell can be cast by this clazz. It is a delegator for
	 * {@link I_SpellStrategy#canCast(SpellBase)}.
	 *
	 * @param Spell the Spell you want to test.
	 *
	 * @return True if class can cast this spell false if not
	 *
	 * @see I_Spell
	 * @see I_SpellStrategy
	 */
	public boolean canCast(SpellBase<?> Spell) { return this.SpellStrategy.canCast(Spell); }
	/**
	 * Delegate Method
	 * @return a List of spells in memory
	 * @see I_SpellStrategy#getSpellsInMemory()
	 */
	public List<SpellBase<?>> getSpellsInMemory() { return SpellStrategy.getSpellsInMemory(); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to test
	 * @return true if memorize-able false if not
	 * @see I_SpellStrategy#canCommitToMemory(SpellBase)
	 */
	public boolean canCommitToMemory(SpellBase<?> Spell) { return SpellStrategy.canCommitToMemory(Spell); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to test
	 * @return true if learn-able false if not
	 * @see I_SpellStrategy#canLearn(SpellBase)
	 */
	public boolean canLearn(SpellBase<?> Spell) { return SpellStrategy.canLearn(Spell); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to learn
	 * @see I_SpellStrategy#learnSpell(SpellBase)
	 */
	public void learnSpell(SpellBase<?> Spell) { SpellStrategy.learnSpell(Spell); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to memorize
	 * @see I_SpellStrategy#memorizeSpell(SpellBase)
	 */
	public void memorizeSpell(SpellBase<?> Spell) { SpellStrategy.memorizeSpell(Spell); }

	// SavingThrowModifier

	/**the modifier used for fortitude saves*/
	private SimpleIntegerDecorator fortitudeSaveModifier;
	/**the modifier used for reflex saves*/
	private SimpleIntegerDecorator reflexSaveModifier;
	/**the modifier used for will saves*/
	private SimpleIntegerDecorator willSaveModifer;

	/**@return the fortitudeSaveModifier value*/
	public final int getFortitudeSaveModifierValue() { return this.fortitudeSaveModifier.getValue(); }
	/**@param fortitudeSaveModifier the fortitudeSaveModifier to set*/
	protected final void setFortitudeSaveModifier(int fortitudeSaveModifier) {
		this.fortitudeSaveModifier.setModifier(fortitudeSaveModifier);
	}
	/**@return the reflexSaveModifier value*/
	public final int getReflexSaveModifierValue() { return this.reflexSaveModifier.getValue(); }
	/**@param reflexSaveModifier the reflexSaveModifier to set*/
	protected final void setReflexSaveModifier(int reflexSaveModifier) {
		this.fortitudeSaveModifier.setModifier(reflexSaveModifier);
	}
	/**@return the willSaveModifer value*/
	public final int getWillSaveModiferValue() { return this.willSaveModifer.getValue(); }
	/**@param willSaveModifer the willSaveModifer to set*/
	protected final void setWillSaveModifer(int willSaveModifer) {
		this.fortitudeSaveModifier.setModifier(willSaveModifer);
	}
	/**@return the fortitudeSaveModifier*/
	public final SimpleIntegerDecorator getFortitudeSaveModifier() { return this.fortitudeSaveModifier; }
	/**@return the reflexSaveModifier*/
	public final SimpleIntegerDecorator getReflexSaveModifier() { return this.reflexSaveModifier; }
	/**@return the willSaveModifer*/
	public final SimpleIntegerDecorator getWillSaveModifer() { return this.willSaveModifer; }


	//name + id + class skills

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

	/**the class skills of this class*/
	private final List<Skills> classSkills;
	/**
	 * used to access the class skills list
	 * @return the {@link #classSkills} object
	 */
	public List<Skills> getClassSkills() { return this.classSkills; }

}
