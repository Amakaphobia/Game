package entity.basic.attributeSet;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;

import entity.basic.common.I_SimpleDecorator;
import entity.basic.common.enums.skillsattributes.Attributes;

/**
 * This Interface provides basic implementation for Attributes it extends {@link I_SimpleDecorator} and {@link Iterable}
 * @author Dave
 *
 */
public interface I_Attribute extends I_SimpleDecorator<I_Attribute, Integer>, Iterable<I_Attribute>, Serializable{

	/**
	 * Used internally to calculate the derived modifier of a given Attribute
	 * @return the derived modifier calculated as follows: <br>
	 * 			<code>(value - 10)/2 rounded down </code>
	 */
	public default int calcDerivedModifier() {
		return (int) Math.floor( (this.getValue() - 10) / 2d );
	}


	/**
	 * Used to expose the IntegerProperty
	 * @return the valueProperty
	 */
	public abstract IntegerProperty valueProperty();

	/**
	 * used to set the level of this attribute
	 * @param value the value you want the attribute to be set to
	 */
	public default void setValue(int value) { this.valueProperty().set(value); }

	/**
	 * used to access the {@link IntegerProperty} containing the derivedModifier property of this attribute
	 * @return the Property object that contains the derived modifier
	 */
	public abstract IntegerProperty derivedModifierProperty();

	/**
	 * used to access the value of the {@link I_Attribute#derivedModifierProperty}.
	 * @return the derived modifier as an integer
	 */
	public default int getDerivedModifier() { return (int)Math.floor(this.derivedModifierProperty().get()); }

	/**
	 * This method gets the derived modifier of a given attribute as a string in form of "+/- modifer"
	 * @return the derivedModifier string
	 */
	public default String getDerivedAttributeModifierAsString() {
		if(this.getDerivedModifier() == 0)
			return "";

		StringBuilder strb = new StringBuilder();
		int modifier = this.getDerivedModifier();
		strb.append(modifier > 0 ? "+ " : "- ");
		if(modifier < 0)
			modifier *= -1;
		strb.append(modifier);
		return strb.toString();
	}

	/**
	 * Used to access the name of the attribute
	 * @return the name as a string
	 */
	public default String getNameId() { return this.getName().getId(); }
	/**
	 * Used to access the Id
	 * @return the Attribute Id
	 */
	public abstract Attributes getName();
}
