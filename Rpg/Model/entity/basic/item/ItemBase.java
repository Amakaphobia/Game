package entity.basic.item;

import entity.basic.EntityBase;
import entity.basic.common.enums.item.ItemSlot;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ItemBase extends EntityBase {

	//TODO war's das scho?
	
	// Constructor
	
	public ItemBase(
			String name, String bildPath, String description, 
			int weight, ItemSlot slot, boolean wearable, int itemGroupId) {
		
		super(name, bildPath, description);
		this.weight = new SimpleIntegerProperty(weight);
		this.slot = slot;
		this.wearable = wearable;
		this.itemGroupId = itemGroupId;
	}
	
	// Properties
	
	private final IntegerProperty weight;
	public final int getWeight() { return this.weight.get(); }
	public final IntegerProperty weightProperty() { return this.weight; }
	
	private final ItemSlot slot;
	public ItemSlot getSlot() { return this.slot; }
	
	private final boolean wearable;
	public boolean isWearable() { return this.wearable; }
	
	private final int itemGroupId;
	public final int getItemGroupId() { return this.itemGroupId; }
	
	// Object
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof ItemBase)) return false;
		
		ItemBase other = (ItemBase) obj;
		
		return this.wearable == other.wearable
			&& this.weight == other.weight
			&& this.slot.equals(other.slot)
			&& super.equals(other);
	}
}
