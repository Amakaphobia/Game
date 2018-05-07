package entity.basic.item;

import entity.basic.item.group.ItemGroup;

@SuppressWarnings("javadoc")
public abstract class WeaponBase extends ItemBase {

	//TODO WeaponBase

	public WeaponBase(
			String name, String bildPath, String description,
			int weight, int itemValue, ItemSlot slot, boolean wearable, ItemGroup itemGroupId) {

		super(name, bildPath, description, weight, itemValue, slot, wearable, itemGroupId);
	}

}
