package entity.spellbook;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import event.I_EventTarget;

/**
 * Simple Base implementation for Spell effects.
 * range in feet and time in rounds
 * @author Dave
 *
 * @param <T> the type of the event target.
 */
public abstract class SpellBase<T extends I_EventTarget> implements I_Spell<T> {

	//TODO wirkungsbereich

	//TODO rettungswurf? maybe part of event

	//TODO spelltype? mage cleric distinction?

	/**
	 * Constructor
	 * @param spellLevel the spell level of the Spell
	 * @param SpellName the name of the spell
	 * @param description the descirption of the spell
	 * @param range the range in feet
	 * @param castingTime the castingTime in rounds
	 */
	public SpellBase(
			int spellLevel,
			String SpellName,
			String description,
			int range,
			int castingTime) {
		super();

		this.spellLevel = new SimpleIntegerProperty(spellLevel);
		this.spellName = new SimpleStringProperty(SpellName);
		this.description = new SimpleStringProperty(description);
		this.range = new SimpleIntegerProperty(range);
		this.castingTime = new SimpleIntegerProperty(castingTime);
	}

	/**holds the minimal spell level of this spell*/
	private final IntegerProperty spellLevel;
	/**@return the {@link #spellLevel} value*/
	public final int getSpellLevel() { return this.spellLevel.get(); }
	/**@return the {@link #spellLevel} property*/
	public final IntegerProperty spellLevelProperty() { return this.spellLevel; }

	/**holds the name of the spell*/
	private final StringProperty spellName;
	/**@return the {@link #spellName} value*/
	public final String getSpellName() { return this.spellName.get(); }
	/**@return the {@link #spellName} property*/
	public final StringProperty spellNameProperty() { return this.spellName; }

	/**holds the description of the spell*/
	private final StringProperty description;
	/**@return the {@link #description} value*/
	public final String getDescription() { return this.description.get(); }
	/**@return the {@link #description} property*/
	public final StringProperty descriptionProperty() { return this.description; }

	/**holds the range (in feet) of the spell*/
	private final IntegerProperty range;
	/**@return the {@link #range} value*/
	public final int getRange() { return this.range.get(); }
	/**@return the {@link #range} property*/
	public final IntegerProperty rangeProperty() { return this.range; }

	/**holds the castingTime (in rounds) of the spell*/
	private final IntegerProperty castingTime;
	/**@return the {@link #castingTime} value*/
	public final int getCastingTime() { return this.castingTime.get(); }
	/**@return the {@link #castingTime} property*/
	public final IntegerProperty castingTimeProperty() { return this.castingTime; }
}
