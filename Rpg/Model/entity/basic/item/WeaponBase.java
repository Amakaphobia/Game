package entity.basic.item;

import common.damage.Damage;
import dicemachine.I_DiceCode;
import entity.basic.item.group.ItemGroup;

@SuppressWarnings("javadoc") //TODO Docu
public abstract class WeaponBase extends ItemBase {

	//TODO WeaponBase
	//TODO RangedWeapon, CC WEapon beide nicht abstrakt
	//TODO Factory


	private final Damage Damage;
	public Damage getDamage() { return this.Damage; }

	private final I_DiceCode hitDieModifier;
	public I_DiceCode getHitDieModifier() { return this.hitDieModifier; }

	public WeaponBase(
			String name, String bildPath, String description,
			ItemGroup itemGroupId, int weight, int itemValue, ItemSlot slot, boolean wearable,
			Damage Damage, I_DiceCode hitDieModifier) {

		super(name, bildPath, description, itemGroupId, weight, itemValue, slot, wearable);
		this.Damage = Damage;
		this.hitDieModifier = hitDieModifier;
	}

}
