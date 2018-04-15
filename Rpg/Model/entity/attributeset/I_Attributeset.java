package entity.attributeset;

import javafx.beans.property.IntegerProperty;

/**
 * This Interface is implemented by attributesets of entities.
 * @author Dave
 *
 */
public interface I_Attributeset {
/*
 * O------------------------------------------------------------------------------------------------------------------O
 * |																												  |
 * |	PropertyAccessors																							  |
 * |																												  |
 * O------------------------------------------------------------------------------------------------------------------O
 */
	/**
	 * This Method exposes the Observable Property for the strength value
	 * @return IntegerProperty for the strength value.
	 */
	public abstract IntegerProperty strengthProperty();
	/**
	 * This Method exposes the Observable Property for the perception value
	 * @return IntegerProperty for the perception value.
	 */
	public abstract IntegerProperty perceptionProperty();
	/**
	 * This Method exposes the Observable Property for the agility value
	 * @return IntegerProperty for the agility value.
	 */
	public abstract IntegerProperty agilityProperty();
	/**
	 * This Method exposes the Observable Property for the endurance value
	 * @return IntegerProperty for the endurance value.
	 */
	public abstract IntegerProperty enduranceProperty();
	/**
	 * This Method exposes the Observable Property for the intelligence value
	 * @return IntegerProperty for the intelligence value.
	 */
	public abstract IntegerProperty intelligenceProperty();
	/**
	 * This Method exposes the Observable Property for the wisdom value
	 * @return IntegerProperty for the wisdom value.
	 */
	public abstract IntegerProperty wisdomProperty();

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

























