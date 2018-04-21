package entity.basic.skillSet;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import entity.basic.attributeSet.Attributes;

/**
 * This class is used to represent a Skill. It holds a Name (Swimming, Performing, Smithing) and the associated value.
 * see: {@link Skills}
 * @author Dave
 *
 */
public class Skill implements Serializable{

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

	/**
	 * used to access the untrained value of this skills {@link Skills}.
	 * @return true if this skill can be attempted untrained.
	 */
	public boolean isUntrained() { return this.name.isUntrained(); }

	/**
	 * this method is used to expose the {@link Skill#name} to the outside.
	 * @return the {@link Skills} id.
	 */
	public Skills getName() { return this.name; }

	/**
	 * Used to access the name of the skill
	 * @return the name as a string
	 */
	public String getNameId() { return this.name.getId(); }

	/**
	 * Used to expose the IntegerProperty {@link Skill#value}
	 * @return the valueProperty
	 */
	public IntegerProperty valueProperty() { return this.value; }
	/**
	 * this is used to expose the value of the {@link Skill#value} property.
	 * @return the Integer value of this Skills level
	 */
	public int getValue() { return this.value.get(); }
	/**
	 * this method is used to set the value of this skills level {@link Skill#value}.
	 * @param value the new level for this skill.
	 */
	public void setValue(int value) { this.value.set(value); }
	/**
	 * This method is used to identify the {@link Attributes} that effects this Skill
	 * @return the Attribute identifier
	 */
	public Attributes getMainAttribute() { return this.name.getMainAttribute(); }

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

	/**
	 * This static method is used to create a dummy instance of the Skill class
	 * @return a Skill containing nothing but tits and twaddle.
	 */
	public static Skill empty() {
		return new Skill(Skills.EMPTY, 0);
	}
}
