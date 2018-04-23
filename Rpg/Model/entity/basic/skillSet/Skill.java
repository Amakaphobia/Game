package entity.basic.skillSet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import common.decorator.DecoratorBase;
import entity.basic.common.enums.skillsattributes.Skills;

/**
 * This class is used to represent a Skill. It holds a Name (Swimming, Performing, Smithing) and the associated value.
 * see: {@link Skills}
 * @author Dave
 *
 */
public class Skill extends DecoratorBase<I_Skill, Integer> implements I_Skill{

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

	@Override
	public Integer getValue() {
		int sum = 0;
		for(I_Skill e : this)
			sum += e.valueProperty().get();
		return sum;
	}

	@Override
	public Skills getName() { return this.name; }

	@Override
	public IntegerProperty valueProperty() { return this.value; }

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
		int value = Integer.hashCode(this.value.get());
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
