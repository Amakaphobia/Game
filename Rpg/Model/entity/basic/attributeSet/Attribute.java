package entity.basic.attributeSet;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import entity.basic.common.enums.skillsattributes.Attributes;

/**
 * This class is used to represent an Attribute. It holds a Name (strength, dexterity, constitution,
 * intelligence , wisdom, charisma) and the associated value.
 * @author Dave
 *
 */
public class Attribute implements Serializable
{
	//TODO TEST
	private Attribute Decorator;

	public void addDecorator(Attribute Decorator) {
		if(this.Decorator == null) {
			this.Decorator = Decorator;
			this.derivedModifier.set(this.calcDerivedModifier());
		}
		else
			this.Decorator.addDecorator(Decorator);
	}

	public void removeFirstDecorator(Attribute other) {
		if(this.Decorator == null) return;
		if(this.Decorator.equals(other)) {
			this.Decorator = other.Decorator;
			this.derivedModifier.set(this.calcDerivedModifier());
		}
		else
			this.Decorator.removeFirstDecorator(other);
	}



	/**holds the value of the Attribute*/
	private final IntegerProperty value;
	/**holds the name of the Attribute*/
	private final Attributes name;
	/**holds the derived modifier<br>the derived modifier is defined as the attributevalue subtracted by 10 and halfed (rounded down)*/
	private final IntegerProperty derivedModifier;
	/**
	 * Constructor
	 * @param name the name of the Attribute
	 * @param value the value
	 */
	public Attribute(Attributes name, int value) {
		this.value = new SimpleIntegerProperty();
		this.derivedModifier = new SimpleIntegerProperty();

		this.value.addListener((ov, oldVal, newVal) ->
			this.derivedModifier.set(this.calcDerivedModifier()));

		this.value.set(value);

		this.name = name;
	}

	/**
	 * Used internally to calculate the derived modifier of a given Attribute
	 * @return
	 */
	private int calcDerivedModifier() {
		return (int) Math.floor( (this.getValue() - 10) / 2d );
	}

	/**
	 * Used to expose the IntegerProperty
	 * @return the valueProperty
	 */
	public IntegerProperty valueProperty() { return this.value; }
	/**
	 * return the level of this attribute as a Integer
	 * @return the level of this attribute
	 */
	public int getValue() {
		return this.Decorator == null ?
				this.value.get() :
				this.value.get() + this.Decorator.getValue(); }
	/**
	 * used to set the level of this attribute
	 * @param value the value you want the attribute to be set to
	 */
	public void setValue(int value) { this.value.set(value); }

	/**
	 * used to access the {@link Attribute#derivedModifier} property of this attribute
	 * @return the Property object that contains the derived modifier
	 */
	public IntegerProperty derivedModifierProperty() { return this.derivedModifier; }
	/**
	 * used to access the value of the {@link Attribute#derivedModifier}.
	 * @return the derived modifier as an integer
	 */
	public int getDerivedModifier() { return (int)Math.floor(this.derivedModifier.get()); }

	/**
	 * This method gets the derived modifier of a given attribute as a string in form of "+/- modifer"
	 * @return the derivedModifier string
	 */
	public String getDerivedAttributeModifierAsString() {
		if(this.getDerivedModifier() == 0)
			return "";

		StringBuilder strb = new StringBuilder();
		int modifier = this.getDerivedModifier();
		strb.append(modifier >= 0 ? "+ " : "- ");
		if(modifier < 0)
			modifier *= -1;
		strb.append(modifier);
		return strb.toString();
	}

	/**
	 * Used to access the name of the attribute
	 * @return the name as a string
	 */
	public String getNameId() { return this.name.getId(); }
	/**
	 * Used to access the Id
	 * @return the Attribute Id
	 */
	public Attributes getName() { return this.name; }

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof Attribute)) return false;

		Attribute other = (Attribute) obj;

		return this.value.get() == other.value.get() &&
				this.name.equals(other.name);
	}
	@Override
	public int hashCode() {
		int value = Integer.hashCode(this.value.get());
		int name = this.name.hashCode();
		return value + name*name;
	}
	@Override
	public String toString() {
		return String.format("%s: %s", this.name.getId(), this.value.get());
	}
}
