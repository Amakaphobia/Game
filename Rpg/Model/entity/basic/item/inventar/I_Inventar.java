package entity.basic.item.inventar;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import entity.basic.item.ItemBase;

@SuppressWarnings("javadoc") //TODO DOCU
public interface I_Inventar extends Iterable<ItemBase>{

	//TODO TEST

	public abstract int getBagSize();

	public abstract List<ItemBase> getBag();

	public abstract boolean put(ItemBase e);

	public abstract boolean remove(Object o);

	public default int getWeight() {
		return this.stream()
					.mapToInt(IB -> IB.getWeight())
					.sum();
	}

	public default double getValue() {
		return this.stream()
					.mapToDouble(IB -> IB.getItemValue())
					.sum();
	}

	@Override
	public default Iterator<ItemBase> iterator(){ return this.getBag().iterator(); }

	public default Stream<ItemBase> stream(){ return this.getBag().stream(); }
}