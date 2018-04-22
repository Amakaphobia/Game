package entity.basic.race;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.size.Sizes;

@SuppressWarnings("javadoc") // TODO docu
public abstract class RaceBase {

	protected final StringProperty name;
	public StringProperty nameProperty() { return this.name; }
	public String getName() { return this.nameProperty().get(); }
	public void setName(String name) { this.nameProperty().set(name); }

	protected I_AttributeSet attributeModifier;
	public I_AttributeSet getAttributeModifier() { return this.attributeModifier; }

	protected final Sizes DefaultSize;
	/**@return the defaultSize*/
	public Sizes getDefaultSize() { return this.DefaultSize; }


	//http://paizo.com/pathfinderRPG/prd/coreRulebook/races.html

	//TODO Weaponfamiliarity
	//TODO Languages
	//TODO traits ?!

	//Constructor

	public RaceBase(String name, Sizes DefaultSize) {
		super();
		this.name = new SimpleStringProperty(name);
		this.DefaultSize = DefaultSize;
		this.attributeModifier = this.buildRacialAttributes();
	}


	protected abstract I_AttributeSet buildRacialAttributes();


}
