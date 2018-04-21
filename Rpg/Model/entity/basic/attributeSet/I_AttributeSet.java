package entity.basic.attributeSet;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;

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
	 * This Method exposes the Observable Property for the strength value
	 * @return IntegerProperty for the strength value.
	 */
	public default IntegerProperty strengthProperty() {
		return this.getAttribute(Attributes.STRENGTH).valueProperty();
	}
	/**
	 * This Method exposes the Observable Property for the perception value
	 * @return IntegerProperty for the perception value.
	 */
	public default IntegerProperty perceptionProperty() {
		return this.getAttribute(Attributes.PERCEPTION).valueProperty();
	}
	/**
	 * This Method exposes the Observable Property for the agility value
	 * @return IntegerProperty for the agility value.
	 */
	public default IntegerProperty agilityProperty() {
		return this.getAttribute(Attributes.AGILITY).valueProperty();
	}
	/**
	 * This Method exposes the Observable Property for the endurance value
	 * @return IntegerProperty for the endurance value.
	 */
	public default IntegerProperty enduranceProperty() {
		return this.getAttribute(Attributes.ENDURANCE).valueProperty();
	}
	/**
	 * This Method exposes the Observable Property for the intelligence value
	 * @return IntegerProperty for the intelligence value.
	 */
	public default IntegerProperty intelligenceProperty() {
		return this.getAttribute(Attributes.INTELLIGENCE).valueProperty();
	}
	/**
	 * This Method exposes the Observable Property for the wisdom value
	 * @return IntegerProperty for the wisdom value.
	 */
	public default IntegerProperty wisdomProperty() {
		return this.getAttribute(Attributes.WISDOM).valueProperty();
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
	public default int getStrength() { return this.strengthProperty().get(); }
	/**
	 * Getter Method for the perception value
	 * @return a the integer inside the perception property
	 */
	public default int getPerception() { return this.perceptionProperty().get(); }
	/**
	 * Getter Method for the agility value
	 * @return a the integer inside the agility property
	 */
	public default int getAgility() { return this.agilityProperty().get(); }
	/**
	 * Getter Method for the endurance value
	 * @return a the integer inside the endurance property
	 */
	public default int getEndurance() { return this.enduranceProperty().get(); }
	/**
	 * Getter Method for the intelligence value
	 * @return a the integer inside the intelligence property
	 */
	public default int getIntelligence() { return this.intelligenceProperty().get(); }
	/**
	 * Getter Method for the wisdom value
	 * @return a the integer inside the wisdom property
	 */
	public default int getWisdom() { return this.wisdomProperty().get(); }

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
	public default void setStrength(int strength) { this.strengthProperty().set(strength); }
	/**
	 * setter Method for the perception value
	 * @param perception the value
	 */
	public default void setPerception(int perception) { this.perceptionProperty().set(perception); }
	/**
	 * setter Method for the agility value
	 * @param agility the value
	 */
	public default void setAgility(int agility) { this.agilityProperty().set(agility); }
	/**
	 * setter Method for the endurance value
	 * @param endurance the value
	 */
	public default void setEndurance(int endurance) { this.enduranceProperty().set(endurance); }
	/**
	 * setter Method for the intelligence value
	 * @param intelligence the value
	 */
	public default void setIntelligence(int intelligence) { this.intelligenceProperty().set(intelligence); }
	/**
	 * setter Method for the wisdom value
	 * @param wisdom the value
	 */
	public default void setWisdom(int wisdom) { this.wisdomProperty().set(wisdom); }
}

























