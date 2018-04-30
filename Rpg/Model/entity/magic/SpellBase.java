package entity.magic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import entity.basic.common.enums.MagicSchools;
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

	//TODO spelltype? mage cleric distinction? class?
	
	//TODO spelltype school

	/**
	 * Constructor
	 * @param spellLevel the spell level of the Spell
	 * @param SpellName the name of the spell
	 * @param description the descirption of the spell
	 * @param range the range in feet
	 * @param castingTime the castingTime in rounds
	 */
	public SpellBase(
			MagicSchools school,
			int spellLevel,
			String SpellName,
			String description,
			int range,
			int castingTime) {
		super();

		this.school = new SimpleObjectProperty<>(school);
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
	
	
	private final ObjectProperty<MagicSchools> school;
	public final MagicSchools getSchool() { return this.school.get(); }
	public final ObjectProperty<MagicSchools> schoolProperty() { return this.school; }

	//Object

	@Override
	public String toString() {
		return String.format("Level %s Spell: %s", this.getSpellLevel(), this.getSpellName());
	}

	@Override
	public int hashCode() { return this.getSpellName().hashCode(); }

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof SpellBase<?>)) return false;

		@SuppressWarnings("unchecked")
		SpellBase<T> other = (SpellBase<T>) obj;
		return this.getSpellName().equals(other.getSpellName());
	}
}
