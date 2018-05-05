package entity.basic.traits;

import entity.basic.EntityBase;

@SuppressWarnings("javadoc") //TODO Docu
public abstract class TraitBase<T extends EntityBase> implements I_Trait<T>{

	private final int id;
	private static int nextId = 0;
	public final int getId() { return this.id; }

	protected I_Trait<? super T> Trait;

	public TraitBase(I_Trait<? super T> Trait) {
		this.Trait = Trait;
		this.id = nextId++;
	}


	//I_Trait Delegate

	@Override
	public void applyTo(T TraitTarget) { this.Trait.applyTo(TraitTarget); }

	@Override
	public void removeFrom(T traitTarget) { this.Trait.removeFrom(traitTarget); }

	//obj

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof TraitBase)) return false;

		@SuppressWarnings("unchecked")
		TraitBase<? extends EntityBase> other = (TraitBase<? extends EntityBase>)obj;
		return this.id == other.id;
	}

	@Override
	public String toString() { return String.valueOf(this.id); }

	@Override
	public int hashCode() {
		return Integer.hashCode(this.id);
	}
}
