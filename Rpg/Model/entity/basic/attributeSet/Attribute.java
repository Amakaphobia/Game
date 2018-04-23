package entity.basic.attributeSet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import common.decorator.DecoratorBase;
import entity.basic.common.enums.skillsattributes.Attributes;

/**
 * This class is used to represent an Attribute. It holds a Name (strength, dexterity, constitution,
 * intelligence , wisdom, charisma) and the associated value.
 * @author Dave
 *
 */
public class Attribute extends DecoratorBase<I_Attribute, Integer> implements I_Attribute
{

	/**holds the value of the Attribute*/
	private final IntegerProperty value;
	/**holds the name of the Attribute*/
	private final Attributes name;
	/**holds the derived modifier<br>the derived modifier is defined as the AttributeValue subtracted by
	 * 10 and halved (rounded down)*/
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

	//I_Attribute

	@Override
	public IntegerProperty valueProperty() { return this.value; }

	@Override
	public IntegerProperty derivedModifierProperty() { return this.derivedModifier; }

	@Override
	public Attributes getName() { return this.name; }

	//Decorator

	@Override
	public Integer getValue() {
		int sum = 0;
		for(I_Attribute e : this)
			sum += e.valueProperty().get();
		return sum;
	}

	@Override
	protected void onAfterAdding() {
		this.derivedModifier.set(this.calcDerivedModifier());
	}

	@Override
	protected void onAfterRemoving() {
		this.derivedModifier.set(this.calcDerivedModifier());
	}

	//Obj

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
