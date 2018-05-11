package entity.basic.item.inventar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entity.basic.item.ItemBase;
import entity.basic.item.ItemSlot;

@SuppressWarnings("javadoc") //TODO Docu
public class BodyWear implements I_Inventar{

	//TODO TEST

	private final Map<ItemSlot, ItemBase> bag;

	public BodyWear() {
		this.bag = new HashMap<>();
	}

	@Override
	public int getBagSize() {
		return ItemSlot.values().length -1; //-1 slotless
	}

	@Override
	public List<ItemBase> getBag() {
		return this.bag.values()
					.stream()
					.collect(Collectors.toList());
	}

	@Override
	public boolean put(ItemBase e) {
		ItemSlot slot = e.getSlot();
		if(! slot.equals(ItemSlot.SLOTLESS) ) return false; //SLOTLESS Cant be equipped

		if(this.bag.containsKey(slot))
			this.remove(e); //un-equip if slot is used

		this.bag.put(slot, e);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Uneqip into inventory? maybe parent.getBagInventory implementation
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof I_Inventar)) return false;

		I_Inventar other = (I_Inventar)obj;
		return this.getBagSize() == other.getBagSize()
			&&  this.getBag().equals(other.getBag());
	}

	@Override
	public String toString() { return this.bag.toString(); }

	@Override
	public int hashCode() { return this.bag.hashCode(); }
}
