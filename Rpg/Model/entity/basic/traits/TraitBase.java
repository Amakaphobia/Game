package entity.basic.traits;

import entity.basic.EntityBase;

/**
 * BaseClass for Traits handles equality and stuff.
 * @author Dave
 *
 * @param <T> the type of the trait target
 */
public abstract class TraitBase<T extends EntityBase> implements I_Trait<T>{

	/**the trait id unique for each trait*/
	private final int id;
	/**the id that the next trait will get*/
	private static int nextId = 0;
	/**@return use this to access this traits id*/
	public final int getId() { return this.id; }

	/**Constructor*/
	public TraitBase() {
		this.id = nextId++;
	}

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
