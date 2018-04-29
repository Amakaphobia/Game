package entity.spellbook;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import event.I_EventTarget;

/**
 * Simple Base implementation for Spell effects.
 * @author Dave
 *
 * @param <T> the type of the event target.
 */
public abstract class SpellBase<T extends I_EventTarget> implements I_Spell<T> {

	//TODO docu

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
		this.reichweite = new SimpleIntegerProperty(range);
		this.castingTime = new SimpleIntegerProperty(castingTime);
	}

	private final IntegerProperty spellLevel;
	public final int getSpellLevel() { return this.spellLevel.get(); }
	public final IntegerProperty spellLevelProperty() { return this.spellLevel; }

	private final StringProperty spellName;
	public final String getSpellName() { return this.spellName.get(); }
	public final StringProperty spellNameProperty() { return this.spellName; }

	private final StringProperty description;
	public final String getDescription() { return this.description.get(); }
	public final StringProperty descriptionProperty() { return this.description; }

	private final IntegerProperty reichweite;
	public final int getReichweite() { return this.reichweite.get(); }
	public final IntegerProperty reichweiteProperty() { return this.reichweite; }

	private final IntegerProperty castingTime;
	public final int getCastingTime() { return this.castingTime.get(); }
	public final IntegerProperty castingTimeProperty() { return this.castingTime; }
}
