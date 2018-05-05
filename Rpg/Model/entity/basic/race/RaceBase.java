package entity.basic.race;

import java.util.Collections;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.size.Sizes;
import entity.basic.traits.TraitBase;

@SuppressWarnings("javadoc") // TODO docu
public abstract class RaceBase {

	private final StringProperty name;
	public StringProperty nameProperty() { return this.name; }
	public String getName() { return this.nameProperty().get(); }
	public void setName(String name) { this.nameProperty().set(name); }

	private I_AttributeSet attributeModifier;
	public I_AttributeSet getAttributeModifier() { return this.attributeModifier; }

	private final Sizes DefaultSize;
	/**@return the defaultSize*/
	public Sizes getDefaultSize() { return this.DefaultSize; }

	private final List<TraitBase<?>> racialTraits;
	public final List<TraitBase<?>> getRacialTraits() { return this.racialTraits; }


	//http://paizo.com/pathfinderRPG/prd/coreRulebook/races.html

	//TODO Weaponfamiliarity
	//TODO Languages

	//Constructor

	public RaceBase(String name, Sizes DefaultSize) {
		super();
		this.name = new SimpleStringProperty(name);
		this.DefaultSize = DefaultSize;
		this.attributeModifier = this.buildRacialAttributes();
		this.racialTraits = Collections.unmodifiableList(this.buildRacialTraits());


	}


	protected abstract I_AttributeSet buildRacialAttributes();

	protected abstract List<TraitBase<?>> buildRacialTraits();
}
