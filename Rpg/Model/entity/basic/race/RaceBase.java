package entity.basic.race;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import entity.basic.common.SizeLogic;
import entity.basic.common.enums.size.Sizes;

@SuppressWarnings("javadoc") // TODO
public abstract class RaceBase {

	protected StringProperty name;
	public StringProperty nameProperty() { return this.name; }
	public String getName() { return this.nameProperty().get(); }
	public void setName(String name) { this.nameProperty().set(name); }

	/**delegate container for size*/
	protected SizeLogic Size;
	/**
	 * @return the current size
	 * @see entity.basic.common.SizeLogic#getSize()
	 */
	public Sizes getSize() { return this.Size.getSize(); }
	/**
	 * @param SizeLogic the {@link Sizes} to set
	 * @see entity.basic.common.SizeLogic#setSize(entity.basic.common.enums.size.Sizes)
	 */
	public void setSize(Sizes Size) { this.Size.setSize(Size); }
	/**@see entity.basic.common.SizeLogic#clearSize()*/
	public void clearSize() {	this.Size.clearSize(); }

	//http://paizo.com/pathfinderRPG/prd/coreRulebook/races.html


	//AttributeModify
	//Weaponfamiliarity
	//Languages
	//traits ?!


	public RaceBase(String name) {
		super();
		this.name = new SimpleStringProperty(name);
	}




}
