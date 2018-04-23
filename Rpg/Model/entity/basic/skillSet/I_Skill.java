package entity.basic.skillSet;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;

import entity.basic.common.I_SimpleDecorator;
import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.common.enums.skillsattributes.Skills;

/**
 * This Interface provides basic implementation for Skills it extends {@link I_SimpleDecorator} and {@link Iterable}
 * @author Dave
 *
 */
public interface I_Skill extends I_SimpleDecorator<I_Skill, Integer>, Iterable<I_Skill>, Serializable
{
	/**
	 * this method is used to expose the {@link Skills} to the outside.
	 * @return the {@link Skills} id.
	 */
	public abstract Skills getName();

	/**
	 * Used to expose the IntegerProperty value
	 * @return the valueProperty
	 */
	public abstract IntegerProperty valueProperty();

	/**
	 * used to access the untrained value of this skills {@link Skills}.
	 * @return true if this skill can be attempted untrained.
	 */
	public default boolean isUntrained() { return this.getName().isUntrained(); }

	/**
	 * Used to access the name of the skill
	 * @return the name as a string
	 */
	public default String getNameId() { return this.getName().getId(); }

	/**
	 * This method is used to identify the {@link Attributes} that effects this Skill
	 * @return the Attribute identifier
	 */
	public default Attributes getMainAttribute() { return this.getName().getMainAttribute(); }

	/**
	 * this method is used to set the value of this skills level {@link Skill#valueProperty}.
	 * @param value the new level for this skill.
	 */
	public default void setValue(int value) { this.valueProperty().set(value); }

}
