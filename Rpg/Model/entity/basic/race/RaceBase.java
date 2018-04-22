package entity.basic.race;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("javadoc")
public abstract class RaceBase {

	protected StringProperty name;
	public StringProperty nameProperty() { return this.name; }
	public String getName() { return this.nameProperty().get(); }
	public void setName(String name) { this.nameProperty().set(name); }

	//http://paizo.com/pathfinderRPG/prd/coreRulebook/races.html

	//size
	//AttributeModify
	//Weaponfamiliarity
	//Languages
	//traits ?!


	public RaceBase(String name) {
		super();
		this.name = new SimpleStringProperty(name);
	}




}
