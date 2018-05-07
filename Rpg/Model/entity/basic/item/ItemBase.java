package entity.basic.item;

import entity.basic.EntityBase;
import entity.basic.item.group.ItemGroup;

/**
 * This is the base Implementation Class for all items.
 *
 * @see EntityBase
 *
 * @author Dave
 *
 */
public abstract class ItemBase extends EntityBase {

	//TODO war's das scho?

	// Constructor

	/**
	 * Constructor
	 *
	 * @see EntityBase#EntityBase(String, String, String)
	 *
	 * @param name the name
	 * @param bildPath the bildPath
	 * @param description the description
	 * @param weight the weight
	 * @param itemValue the value of this item
	 * @param slot the slot
	 * @param wearable true if it can be worn
	 * @param itemGroupId the items group id
	 */
	public ItemBase(
			String name, String bildPath, String description,
			int weight, double itemValue, ItemSlot slot, boolean wearable, ItemGroup itemGroupId) {

		super(name, bildPath, description);
		this.weight = weight;
		this.slot = slot;
		this.wearable = wearable;
		this.itemGroupId = itemGroupId;
		this.itemValue = itemValue;
	}

	/**
	 * Constructor for items that cannot be worn.
	 *
	 * @see EntityBase#EntityBase(String, String, String)
	 *
	 * @param name the name
	 * @param bildPath the bildPath
	 * @param description the description
	 * @param weight the weight
	 * @param itemValue the value of this item
	 * @param itemGroupId the items group id
	 */
	public ItemBase(
			String name, String bildPath, String description,
			int weight, double itemValue, ItemGroup itemGroupId) {
		this(name, bildPath, description, weight, itemValue, ItemSlot.SLOTLESS, false, itemGroupId);
	}

	// Properties

	/**this items weight*/
	private final int weight;
	/**@return the {@link #weight}*/
	public final int getWeight() { return this.weight; }

	/**this items slot*/
	private final ItemSlot slot;
	/**@return {@link #slot}*/
	public final ItemSlot getSlot() { return this.slot; }

	/**true if this item can be worn*/
	private final boolean wearable;
	/**@return {@link #wearable}*/
	public final boolean isWearable() { return this.wearable; }

	/**this items group identifier*/
	private final ItemGroup itemGroupId;
	/**@return {@link #itemGroupId}*/
	public final ItemGroup getItemGroupId() { return this.itemGroupId; }

	/**the value of this item*/
	private final double itemValue;
	/**@return {@link #itemValue}*/
	public final double getItemValue() { return this.itemValue; }

	// Object

	/**<b>does not check entity id</b><br>Items that have the same values are equal.*/
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof ItemBase)) return false;

		ItemBase other = (ItemBase) obj;

		return this.getName().equals(other.getName())
			&& this.wearable == other.wearable
			&& this.weight == other.weight
			&& this.slot.equals(other.slot);
	}
}
