package entity.basic.traits;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import common.render.I_InfoAble;
import entity.basic.EntityBase;

/**
 * BaseClass for Traits handles equality and stuff.
 * @author Dave
 *
 * @param <T> the type of the trait target
 */
public abstract class TraitBase<T extends EntityBase> implements I_Trait<T>, I_InfoAble{

	/**
	 * Constructor
	 * @param name the name
	 * @param description the description
	 */
	public TraitBase(String name, String description) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
	}

	//Properties

	/**the name of the trait*/
	private final StringProperty name;
	/**@return the name property*/
	public final StringProperty nameProperty() { return this.name; }
	/**@return the name as a string*/
	public final String getName() { return this.nameProperty().get(); }

	/**the trait's description*/
	private final StringProperty description;
	/**@return the description Property*/
	public final StringProperty descriptionProperty() { return this.description; }
	/**@return the Description as a String*/
	public final String getDescription() { return this.descriptionProperty().get(); }


	//obj

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof TraitBase)) return false;

		@SuppressWarnings("unchecked")
		TraitBase<? extends EntityBase> other = (TraitBase<? extends EntityBase>)obj;
		return this.getName().equals(other.getName());
	}

	@Override
	public String toString() { return this.getName(); }

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}




}
