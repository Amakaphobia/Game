package entity.basic.skillSet;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This class is used to represent a Skill. It holds a Name (Swimming, Performing, Smithing) and the associated value.
 * see: {@link Skills}
 * @author Dave
 *
 */
public class Skill implements Serializable{
	/**serial*/
	private static final long serialVersionUID = 1418267970463202521L;

	/**holds the name of the Skill*/
	private final Skills name;
	/**holds the value of the Skill*/
	private final IntegerProperty value;

	/**
	 * Constructor
	 * @param name the name of the Skill
	 * @param value the value
	 */
	public Skill(Skills name, int value) {
		this.name = name;
		this.value = new SimpleIntegerProperty(value);
	}
	
	public Skills getName() { return this.name; }

	/**
	 * Used to access the name of the skill
	 * @return the name as a string
	 */
	public String getNameId() { return this.name.getId(); }

	/**
	 * Used to expose the IntegerProperty
	 * @return the valueProperty
	 */
	public IntegerProperty valueProperty() { return this.value; }
	
	public int getValue() { return this.value.get(); }

	public void setValue(int value) { this.value.set(value); }
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof Skill)) return false;

		Skill other = (Skill) obj;
		return this.value.get() == other.value.get() &&
				this.name.equals(other.name);
	}
	@Override
	public String toString() {
		return String.format("%s: %s", this.name.getId(), this.value.get());
	}
	@Override
	public int hashCode() {
		int value = this.value.hashCode();
		int name = this.name.hashCode();
		return value + name*name;
	}
	
	public static Skill empty() {
		return new Skill(Skills.EMPTY, 0);
	}
}
