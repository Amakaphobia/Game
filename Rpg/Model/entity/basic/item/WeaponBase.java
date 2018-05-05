package entity.basic.item;

import entity.basic.common.enums.item.ItemSlot;

@SuppressWarnings("javadoc")
public abstract class WeaponBase extends ItemBase {

	//TODO WeaponBase

	public WeaponBase(
			String name, String bildPath, String description,
			int weight, ItemSlot slot, boolean wearable, int itemGroupId) {
		super(name, bildPath, description, weight, slot, wearable, itemGroupId);
	}

}
