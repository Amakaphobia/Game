package entity.actorBase;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import common.damage.Damage;
import common.damage.DamageType;
import common.map.I_GameMap;
import dicemachine.DiceCodeBase;
import dicemachine.I_DiceCode;
import entity.actorBase.container.HealthPointContainer;
import entity.actorBase.container.I_HasHp;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.clazz.ClazzBase;
import entity.basic.common.enums.alignment.Alignment;
import entity.basic.common.enums.clazz.Clazzs;
import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.race.RaceBase;
import entity.basic.skillSet.I_SkillSet;
import entity.magic.SpellBase;


/**
 * This Level of the Actor handles Health and Class. It implements {@link I_HasHp}.
 *
 * @author Dave
 *
 * @see HealthPointContainer
 * @see ClazzBase
 * @see Clazzs
 */
public abstract class ClassedActorBase extends SkilledActorBase implements I_HasHp{

	//HP Delegate

	/**this container holds the entities Hp*/
	private final HealthPointContainer Hp;
	/**@return this actors HP container*/
	public final HealthPointContainer getHp() { return this.Hp; }
	/**
	 * Delegate Method
	 * @return the Resistance set
	 */
	public Set<DamageType> getResistance() { return this.Hp.getResistance(); }
	/**
	 * Delegate Method
	 * @param type the type
	 * @see HealthPointContainer#addResistance(DamageType)
	 */
	public void addResistance(DamageType type) { this.Hp.addResistance(type); }
	/**
	 * Delegate Method
	 * @param type the type
	 * @see HealthPointContainer#removeResistance(DamageType)
	 */
	public void removeResistance(DamageType type) { this.Hp.removeResistance(type); }
	/**
	 * Delegate Method
	 * @param type the type
	 * @return true if resistant
	 * @see HealthPointContainer#isResistantTo(DamageType)
	 */
	public boolean isResistantTo(DamageType type) { return this.Hp.isResistantTo(type); }
	/**
	 * Delegate Method
	 * @return the immunity set
	 * @see HealthPointContainer#getImmunity()
	 */
	public Set<DamageType> getImmunity() { return this.Hp.getImmunity(); }
	/**
	 * Delegate Method
	 * @param type the type
	 * @see HealthPointContainer#addImmunity(DamageType)
	 */
	public void addImmunity(DamageType type) { this.Hp.addImmunity(type); }
	/**
	 * Delegate Method
	 * @param type the type
	 * @see HealthPointContainer#removeImmunity(DamageType)
	 */
	public void removeImmunity(DamageType type) { this.Hp.removeImmunity(type); }
	/**
	 * Delegate Method
	 * @param type the type
	 * @return true if immune
	 * @see HealthPointContainer#isImmunTo(DamageType)
	 */
	public boolean isImmunTo(DamageType type) { return this.Hp.isImmunTo(type); }
	/**
	 * Delegate Method
	 * @param damage the damage to take
	 * @see HealthPointContainer#takeDamage(List)
	 */
	public void takeDamage(List<Damage> damage) { this.Hp.takeDamage(damage); }

	//Constructor
	/**
	 * Constructor
	 * @param name the name
	 * @param bildPath the bild path
	 * @param Map the map
	 * @param Race the {@link RaceBase}
	 * @param SkillSet the {@link I_SkillSet}
	 * @param AttributeSet the {@link I_AttributeSet}
	 * @param Alignment the {@link Alignment}
	 * @param prefferedClazz the prefferedClazz of this actor
	 *
	 * @see SkilledActorBase#SkilledActorBase(String, String, I_GameMap, RaceBase, I_SkillSet, I_AttributeSet, Alignment)
	 */
	public ClassedActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet,
			Alignment Alignment,
			Clazzs prefferedClazz) {
		this(name, bildPath, Map, Race, SkillSet, AttributeSet, Alignment, prefferedClazz, new ArrayList<>());
	}

	/**
	 * Constructor
	 * @param name the name
	 * @param bildPath the bild path
	 * @param Map the map
	 * @param Race the {@link RaceBase}
	 * @param SkillSet the {@link I_SkillSet}
	 * @param AttributeSet the {@link I_AttributeSet}
	 * @param Alignment the {@link Alignment}
	 * @param prefferedClazz the prefferedClazz of this actor
	 * @param clazzes the classes of this actor
	 *
	 * @see SkilledActorBase#SkilledActorBase(String, String, I_GameMap, RaceBase, I_SkillSet, I_AttributeSet, Alignment)
	 */
	public ClassedActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet,
			Alignment Alignment,
			Clazzs prefferedClazz, List<ClazzBase> clazzes) {
		super(name, bildPath, Map, Race, SkillSet, AttributeSet, Alignment);

		this.Hp = new HealthPointContainer(this);

		this.Clazzes = clazzes;

		this.prefferedClass = prefferedClazz;
	}

	//ClassBase Handling
	//TODO Docu an multiclassing

	/**holds the classes of this actor*/
	protected final List<ClazzBase> Clazzes;
	/**holds the preferred class of this actor*/
	private final Clazzs prefferedClass;

	/**
	 * This method checks if an actor is multiclassed. If the list {@link #Clazzes} has a size
	 * bigger than 1 it returns true otherwise false.
	 * @return true if multiclassed false if not
	 */
	public boolean isMulticlassed() {
		return this.Clazzes.size() > 1;
	}


	/**
	 * Delegate Method
	 * @param index the index of the base attack you want
	 * @return the base attack bonus if the given int
	 * @see ClazzBase#getBaseAttackBonus(int)
	 */
	public int getBaseAttackBonus(int index) {
		return this.Clazzes.stream()
			.mapToInt(c -> c.getBaseAttackBonus(index))
			.sum();
	}


	/**
	 * Delegate Method
	 * @return true if this actor can cast spells false if not
	 * @see ClazzBase#isMagical()
	 */
	public boolean isMagical() {
		return this.Clazzes.stream()
			.map(c -> c.isMagical())
			.collect(reducing(false, (a, b) -> a || b));
	}
	/**
	 * Delegate Method
	 * @return a list of all learned spells
	 * @see ClazzBase#getSpellsLearned()
	 */
	public List<SpellBase<?>> getSpellsLearned() {
		return this.Clazzes.stream()
				.flatMap(c -> c.getSpellsLearned().stream())
				.collect(toList());
	}
	/**
	 * Delegate Method
	 * @return a List of all spells this character can learn
	 * @see ClazzBase#getSpellsComplete()
	 */
	public List<SpellBase<?>> getSpellsComplete() {
		return this.Clazzes.stream()
				.flatMap(c -> c.getSpellsComplete().stream())
				.collect(toList());
	}
	/**
	 * Delegate Method
	 * @param Spell the spell you want to check
	 * @return true if the spell is cast-able
	 * @see ClazzBase#canCast(SpellBase)
	 */
	public boolean canCast(SpellBase<?> Spell) {
		return
			this.Clazzes.stream()
				.filter(ClazzBase::isMagical)
				.filter(c ->
					c.getSpellsInMemory().stream()
						.filter(s -> s.equals(Spell))
						.findFirst()
						.isPresent())
				.findFirst()
				.isPresent();
	}
	/**
	 * Delegate Method
	 * @return all spells currently in memory
	 * @see ClazzBase#getSpellsInMemory()
	 */
	public List<SpellBase<?>> getSpellsInMemory() {
		return this.Clazzes.stream()
					.filter(ClazzBase::isMagical)
					.flatMap(c -> c.getSpellsInMemory().stream())
					.collect(toList());
	}
	/**
	 * Delegate Method
	 * @param Spell the spell you want to test
	 * @return true if you can commit the spell to memory false if not
	 * @see ClazzBase#canCommitToMemory(SpellBase)
	 */
	public boolean canCommitToMemory(SpellBase<?> Spell) {
		return this.Clazzes.stream()
					.filter(ClazzBase::isMagical)
					.filter(c -> c.canCommitToMemory(Spell))
					.findFirst()
					.isPresent();
	}
	/**
	 * Delegate Method
	 * @param Spell the spell you want to learn
	 * @return true if you can learn the spell false if not
	 * @see ClazzBase#canLearn(SpellBase)
	 */
	public boolean canLearn(SpellBase<?> Spell) {
		return this.Clazzes.stream()
					.filter(ClazzBase::isMagical)
					.filter(c -> c.canLearn(Spell))
					.findFirst()
					.isPresent();
	}
	/**
	 * Delegate Method
	 * @param Spell the spell you want to learn
	 * @param clazzId the class of the actor that attempts learning the spell
	 * @see ClazzBase#learnSpell(SpellBase)
	 */
	public void learnSpell(SpellBase<?> Spell, Clazzs clazzId) {
		this.Clazzes.stream()
			.filter(ClazzBase::isMagical)
			.filter(c -> c.getId().equals(clazzId))
			.findFirst()
			.ifPresent(c -> c.learnSpell(Spell));
	}
	/**
	 * Delegate Method
	 * @param Spell the spell you want to memorize
	 * @param clazzId the class of the actor that attempts learning the spell
	 * @see ClazzBase#memorizeSpell(SpellBase)
	 */
	public void memorizeSpell(SpellBase<?> Spell, Clazzs clazzId) {
		this.Clazzes.stream()
			.filter(ClazzBase::isMagical)
			.filter(c -> c.getId().equals(clazzId))
			.findFirst()
			.ifPresent(c -> c.memorizeSpell(Spell));
	}


	/**
	 * Delegate Method
	 * @return the fortitude save modifier of this actor
	 * @see ClazzBase#getFortitudeSaveModifierValue()
	 */
	public int getFortitudeSaveModifierValue() {
		return this.Clazzes.stream()
				.mapToInt(ClazzBase::getFortitudeSaveModifierValue)
				.sum();
	}
	/**
	 * Delegate Method
	 * @return the reflex save modifier of this actor
	 * @see ClazzBase#getReflexSaveModifierValue()
	 */
	public int getReflexSaveModifierValue() {
		return this.Clazzes.stream()
				.mapToInt(ClazzBase::getReflexSaveModifierValue)
				.sum();
	}
	/**
	 * Delegate Method
	 * @return the will save modifier of this actor
	 * @see entity.basic.clazz.ClazzBase#getWillSaveModiferValue()
	 */
	public int getWillSaveModiferValue() {
		return this.Clazzes.stream()
				.mapToInt(ClazzBase::getWillSaveModiferValue)
				.sum();
	}

	/**
	 * Delegate Method
	 * @return the class id of this actor
	 * @see ClazzBase#getId()
	 */
	public List<Clazzs> getClassId() {
		return this.Clazzes.stream()
				.map(ClazzBase::getId)
				.collect(toList());
	}

	//Leveling

	/**
	 * this method is called if one of the actors classes levels up
	 * @param clazz the class that leveled
	 */
	public void onClassLevelUp(ClazzBase clazz) {
		this.onClassLevelUpHitDie(clazz);
	}

	/**
	 * this method is called if one of the actors classes levels up. It handles the HP growth.
	 * @param Clazz the class that leveled
	 */
	protected void onClassLevelUpHitDie(ClazzBase Clazz) {
		I_DiceCode die = DiceCodeBase.flat(0);
		try {
			die = Clazz.getHitDieCode().clone();
		} catch (CloneNotSupportedException e) { e.printStackTrace(); }

		die.addModifier(this.getDerivedAttributeModifier(Attributes.CONSTITUTION));

		if(Clazz.getLevel() == 1)
			this.Hp.addHitDieMax(die);
		else
			this.Hp.addHitDie(die);
	}

	//I_HasHp

	/**
	 * {@inheritDoc}<br>
	 * It will set {@link #setLiving(boolean)} to false.
	 */
	@Override
	public void onHealthZero() { this.setLiving(false); }

	/**
	 * {@inheritDoc}<br>
	 * It will set {@link #setLiving(boolean)} to true.
	 */
	@Override
	public void onHealthNoLongerZero() { this.setLiving(true); }

	//Object

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof ClassedActorBase)) return false;

		ClassedActorBase other = (ClassedActorBase)obj;

		return
				this.Hp.equals(other.Hp)
			&&  this.Clazzes.equals(other.Clazzes)
			&&  this.prefferedClass.equals(other.prefferedClass)
			&&  super.equals(other);
	}
}
