package entity.basic.item.inventar;

import java.util.ArrayList;
import java.util.List;

import entity.basic.item.ItemBase;

@SuppressWarnings("javadoc") //TODO Docu
public class Inventar implements I_Inventar{

	//TODO Test Inventar

	private final int bagSize;
	@Override
	public final int getBagSize() { return this.bagSize; }

	private final List<ItemBase> bag;
	@Override
	public final List<ItemBase> getBag() { return this.bag; }

	public Inventar() {
		this(Integer.MAX_VALUE);
	}

	public Inventar(int BagSize) {
		this.bag = new ArrayList<>();
		this.bagSize = BagSize;
	}

	@Override
	public boolean put(ItemBase e) {
		if(this.bag.size() >= this.bagSize) return false;

		return this.bag.add(e);
	}

	@Override
	public boolean remove(Object o) { return this.bag.remove(o); }

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof I_Inventar)) return false;

		I_Inventar other = (I_Inventar)obj;
		return this.bagSize == other.getBagSize()
			&&  this.bag.equals(other.getBag());
	}

	@Override
	public String toString() { return this.bag.toString(); }

	@Override
	public int hashCode() { return Integer.hashCode(this.bagSize) + this.bag.hashCode(); }
}
