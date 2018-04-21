package entity.basic.attributeSet;

import java.io.Serializable;

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
	 * Getter Method for the perception value
	 * @return a the integer inside the perception property
	 */
	public default int getPerception() { return this.getAttribute(Attributes.PERCEPTION).getValue(); }
	/**
	 * Getter Method for the agility value
	 * @return a the integer inside the agility property
	 */
	public default int getAgility() { return this.getAttribute(Attributes.AGILITY).getValue(); }
	/**
	 * Getter Method for the endurance value
	 * @return a the integer inside the endurance property
	 */
	public default int getEndurance() { return this.getAttribute(Attributes.ENDURANCE).getValue(); }
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
	 * setter Method for the perception value
	 * @param perception the value
	 */
	public default void setPerception(int perception) { this.getAttribute(Attributes.PERCEPTION).setValue(perception); }
	/**
	 * setter Method for the agility value
	 * @param agility the value
	 */
	public default void setAgility(int agility) { this.getAttribute(Attributes.AGILITY).setValue(agility); }
	/**
	 * setter Method for the endurance value
	 * @param endurance the value
	 */
	public default void setEndurance(int endurance) { this.getAttribute(Attributes.ENDURANCE).setValue(endurance); }
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
}

























