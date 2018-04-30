package entity.basic.item;

import entity.basic.EntityBase;
import entity.basic.common.enums.item.ItemSlot;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ItemBase extends EntityBase {

	//TODO war das scho?
	
	// Constructor
	
	public ItemBase(
			String name, String bildPath, String description, 
			int weight, ItemSlot slot) {
		
		super(name, bildPath, description);
		this.weight = new SimpleIntegerProperty(weight);
		this.slot = slot;
	}
	
	// Properties
	
	private final IntegerProperty weight;
	public final int getWeight() { return this.weight.get(); }
	public final IntegerProperty weightProperty() { return this.weight; }
	
	private final ItemSlot slot;
	public ItemSlot getSlot() { return this.slot; }
	
	//
}
