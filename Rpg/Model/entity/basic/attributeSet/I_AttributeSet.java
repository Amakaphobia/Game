package entity.basic.attributeSet;

import java.io.Serializable;

import entity.basic.enums.skillsattributes.Attributes;

/**
 * This Interface is implemented by AttributeSets of entities.
 * @author Dave
 *
 */
public interface I_AttributeSet extends Serializable, Iterable<Attribute>{

/*
 * O------------------------------------------------------------------------------------------------------------------O
 * |																												  |
 * |	PropertyAccessors																							  |
 * |																												  |
 * O------------------------------------------------------------------------------------------------------------------O
 */
	/**
	 * This Method exposes the Observable Property for the given {@link Attributes} value
	 * @param id  {@link Attributes} value
	 * @return the searched {@link Attribute}.
	 */
	public abstract Attribute getAttribute(Attributes id);

	/**
	 * This method gets the derived modifier of a given attribute as a string in form of "+/- modifer"
	 * @param AttributeId the {@link Attributes} searched for
	 * @return the derivedModifier string
	 */
	public default String getDerivedAttributeModifierAsString(Attributes AttributeId) {
		return this.getAttribute(AttributeId).getDerivedAttributeModifierAsString();
	}



/*
 * O------------------------------------------------------------------------------------------------------------------O
 * |																												  |
 * |	Getter for ints																								  |
 * |																												  |
 * O------------------------------------------------------------------------------------------------------------------O
 */
	/**
	 * Getter Method for the strength value
	 * @return a the integer inside the strength property
	 */
	public default int getStrength() { return this.getAttribute(Attributes.STRENGTH).getValue();}
	/**
	 * Getter Method for the agility value
	 * @return a the integer inside the agility property
	 */
	public default int getDexterity() { return this.getAttribute(Attributes.DEXTERITY).getValue(); }
	/**
	 * Getter Method for the endurance value
	 * @return a the integer inside the endurance property
	 */
	public default int getConstitution() { return this.getAttribute(Attributes.CONSTITUTION).getValue(); }
	/**
	 * Getter Method for the intelligence value
	 * @return a the integer inside the intelligence property
	 */
	public default int getIntelligence() { return this.getAttribute(Attributes.INTELLIGENCE).getValue(); }
	/**
	 * Getter Method for the wisdom value
	 * @return a the integer inside the wisdom property
	 */
	public default int getWisdom() { return this.getAttribute(Attributes.WISDOM).getValue(); }
	/**
	 * Getter Method for the charisma value
	 * @return a the integer inside the charisma property
	 */
	public default int getCharisma() { return this.getAttribute(Attributes.CHARISMA).getValue(); }



/*
 * O------------------------------------------------------------------------------------------------------------------O
 * |																												  |
 * |	setter for ints																								  |
 * |																												  |
 * O------------------------------------------------------------------------------------------------------------------O
 */
	/**
	 * setter Method for the strength value
	 * @param strength the value
	 */
	public default void setStrength(int strength) { this.getAttribute(Attributes.STRENGTH).setValue(strength); }
	/**
	 * setter Method for the Dexterity value
	 * @param dexterity the value
	 */
	public default void setDexterity(int dexterity) { this.getAttribute(Attributes.DEXTERITY).setValue(dexterity); }
	/**
	 * setter Method for the endurance value
	 * @param constitution the value
	 */
	public default void setConstitution(int constitution) { this.getAttribute(Attributes.CONSTITUTION).setValue(constitution); }
	/**
	 * setter Method for the intelligence value
	 * @param intelligence the value
	 */
	public default void setIntelligence(int intelligence) { this.getAttribute(Attributes.INTELLIGENCE).setValue(intelligence); }
	/**
	 * setter Method for the wisdom value
	 * @param wisdom the value
	 */
	public default void setWisdom(int wisdom) { this.getAttribute(Attributes.WISDOM).setValue(wisdom); }
	/**
	 * setter Method for the charisma value
	 * @param charisma the value
	 */
	public default void setCharisma(int charisma) { this.getAttribute(Attributes.CHARISMA).setValue(charisma); }
}