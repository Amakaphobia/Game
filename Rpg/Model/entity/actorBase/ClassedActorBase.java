package entity.actorBase;

import java.util.List;

import common.map.I_GameMap;
import entity.actorBase.container.HealthPointContainer;
import entity.actorBase.container.I_HasHp;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.alignment.Alignment;
import entity.basic.common.enums.clazz.Clazzs;
import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.race.RaceBase;
import entity.basic.skillSet.I_SkillSet;
import entity.clazz.ClazzBase;
import entity.clazz.ClazzFactory;
import entity.magic.SpellBase;


/**
 * This Level of the Actor handles Health and Class. It implements {@link I_HasHp}.
 *
 * @author Dave
 */
public abstract class ClassedActorBase extends SkilledActorBase implements I_HasHp{

	/**this container holds the entities Hp*/
	protected final HealthPointContainer Hp;

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
	 * @param clazz the {@link ClazzBase}
	 *
	 * @see SkilledActorBase#SkilledActorBase(String, String, I_GameMap, RaceBase, I_SkillSet, I_AttributeSet, Alignment)
	 */
	public ClassedActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet,
			Alignment Alignment,
			Clazzs clazz) {
		super(name, bildPath, Map, Race, SkillSet, AttributeSet, Alignment);

		this.Hp = new HealthPointContainer(this);

		this.Clazz = ClazzFactory.warriorMock(this); //TODO remove mock
		this.prefferedClass = this.Clazz; //TODO PrefferedCLass of actor
	}

	//ClassBase Handling
	//TODO multi-classing

	/**holds the classes of this actor*/
	protected final ClazzBase Clazz;
	/**holds the preferred class of this actor*/
	private final ClazzBase prefferedClass;

	/**
	 * Delegate Method
	 * @return the hitdie code
	 * @see ClazzBase#getHitDieCode()
	 */
	public String getHitDieCode() { return Clazz.getHitDieCode(); }
	/**
	 * Delegate Method
	 * @param index the index of the base attack you want
	 * @return the base attack bonus if the given int
	 * @see ClazzBase#getBaseAttackBonus(int)
	 */
	public int getBaseAttackBonus(int index) { return Clazz.getBaseAttackBonus(index); }


	/**
	 * Delegate Method
	 * @return true if this actor can cast spells false if not
	 * @see ClazzBase#isMagical()
	 */
	public boolean isMagical() { return Clazz.isMagical(); }
	/**
	 * Delegate Method
	 * @return a list of all learned spells
	 * @see ClazzBase#getSpellsLearned()
	 */
	public List<SpellBase<?>> getSpellsLearned() { return Clazz.getSpellsLearned(); }
	/**
	 * Delegate Method
	 * @return a List of all spells this character can learn
	 * @see ClazzBase#getSpellsComplete()
	 */
	public List<SpellBase<?>> getSpellsComplete() { return Clazz.getSpellsComplete(); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to check
	 * @return true if the spell is castable
	 * @see ClazzBase#canCast(SpellBase)
	 */
	public boolean canCast(SpellBase<?> Spell) { return Clazz.canCast(Spell); }
	/**
	 * Delegate Method
	 * @return all spells currently in memory
	 * @see ClazzBase#getSpellsInMemory()
	 */
	public List<SpellBase<?>> getSpellsInMemory() { return Clazz.getSpellsInMemory(); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to test
	 * @return true if you can commit the spell to memory false if not
	 * @see ClazzBase#canCommitToMemory(SpellBase)
	 */
	public boolean canCommitToMemory(SpellBase<?> Spell) { return Clazz.canCommitToMemory(Spell); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to learn
	 * @return true if you can learn the spell false if not
	 * @see ClazzBase#canLearn(SpellBase)
	 */
	public boolean canLearn(SpellBase<?> Spell) { return Clazz.canLearn(Spell); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to learn
	 * @see ClazzBase#learnSpell(SpellBase)
	 */
	public void learnSpell(SpellBase<?> Spell) { Clazz.learnSpell(Spell); }
	/**
	 * Delegate Method
	 * @param Spell the spell you want to memorize
	 * @see ClazzBase#memorizeSpell(SpellBase)
	 */
	public void memorizeSpell(SpellBase<?> Spell) { Clazz.memorizeSpell(Spell); }


	/**
	 * Delegate Method
	 * @return the fortitude save modifier of this actor
	 * @see ClazzBase#getFortitudeSaveModifierValue()
	 */
	public int getFortitudeSaveModifierValue() { return Clazz.getFortitudeSaveModifierValue(); }
	/**
	 * Delegate Method
	 * @return the reflex save modifier of this actor
	 * @see ClazzBase#getReflexSaveModifierValue()
	 */
	public int getReflexSaveModifierValue() { return Clazz.getReflexSaveModifierValue(); }
	/**
	 * Delegate Method
	 * @return the will save modifier of this actor
	 * @see entity.clazz.ClazzBase#getWillSaveModiferValue()
	 */
	public int getWillSaveModiferValue() { return Clazz.getWillSaveModiferValue(); }

	/**
	 * Delegate Method
	 * @return the class id of this actor
	 * @see ClazzBase#getId()
	 */
	public Clazzs getClassId() { return Clazz.getId(); }

	//Leveling

	/**
	 * this method is called if one of the actors classes levels up
	 * @param clazz the class that leveled
	 */
	public void onClassLevelUp(ClazzBase clazz) {
		this.onClassLevelUpHitDie(clazz);
	}

	/**
	 * this method is called if one of the actors classes levels up. It handles the hitpoint growth.
	 * @param Clazz the class that leveled
	 */
	protected void onClassLevelUpHitDie(ClazzBase Clazz) {
		StringBuilder strb = new StringBuilder(Clazz.getHitDieCode());
		strb.append(this.getDerivedAttributeModifierAsString(Attributes.CONSTITUTION));
		if(Clazz.getLevel() == 1)
			this.Hp.addHitDieMax(strb.toString());
		else
			this.Hp.addHitDie(strb.toString());
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
			&&  this.Clazz.equals(other.Clazz)
			&&  this.prefferedClass.equals(other.prefferedClass)
			&&  super.equals(other);
	}
}
