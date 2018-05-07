package entity.basic.item.group;

import common.decorator.DecoratorBase;

/**
 * ItemGroup Class can be decorated.
 * <br><br>
 * <blockquote>
 * To Create GroupGroups Create the outer group first and the inner second to decorate them with the outer
 * </blockquote>
 *
 * @author Dave
 *
 */
public class ItemGroup extends DecoratorBase<ItemGroup, String>{

	/**holds the GroupName*/
	private final String groupName;

	/**
	 * Constructor
	 * @param GroupName the name of the group
	 */
	public ItemGroup(String GroupName) {
		this.groupName = GroupName;
	}

	@Override
	public String getValue() {
		final String joiner = ": ";
		StringBuilder strb = new StringBuilder();
		for(ItemGroup e : this) {
			StringBuilder strb2 = new StringBuilder(e.groupName);
			strb.append(strb2.reverse())
				.append(joiner);
		}
		strb.delete(strb.length()-joiner.length(), strb.length())
			.reverse();
		return strb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof ItemGroup)) return false;

		ItemGroup other = (ItemGroup)obj;
		return this.getValue().equals(other.getValue());

	}

	@Override
	public int hashCode() {
		return this.getValue().hashCode();
	}

	@Override
	public String toString() {
		return this.getValue();
	}

}
