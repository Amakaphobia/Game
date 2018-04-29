package entity.basic.common;

import common.OverwriteableDefault;
import entity.actorBase.RacialActorBase;
import entity.basic.common.enums.size.Sizes;

/**
 * Holds simple information about an actors body such as size age and stuff
 * @author Dave
 *
 */
public class BodyInformation {

	/**the actor that owns this container*/
	private RacialActorBase Parent;
	/**
	 * use this method to register the parent of this container
	 * @param Parent the parent of this container
	 */
	public void setParent(RacialActorBase Parent) { this.Parent = Parent; }
	/**
	 * used to access the owner of this container
	 * @return {@link RacialActorBase} Parent of this object
	 */
	public RacialActorBase getParent() { return this.Parent; }

	/**
	 * Constructor
	 * @param Size the {@link SizeLogic} container of this actor
	 */
	public BodyInformation(OverwriteableDefault<Sizes> Size) {
		super();
		this.Size = Size;
	}

	//Size

	/**delegate container for size*/
	protected OverwriteableDefault<Sizes> Size;
	/**
	 * @return the current size
	 * @see OverwriteableDefault#get()
	 */
	public Sizes getSize() { return this.Size.get(); }
	/**
	 * @param Size the {@link Sizes} to set
	 * @see OverwriteableDefault#set(Object)
	 */
	public void setSize(Sizes Size) { this.Size.set(Size); }
	/**@see OverwriteableDefault#clearOverwrite()*/
	public void clearSize() {	this.Size.clearOverwrite(); }


	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof BodyInformation)) return false;

		BodyInformation other = (BodyInformation) obj;
		return this.Size.equals(other.Size);
	}

	@Override
	public int hashCode() {
		return this.Size.hashCode();
	}

	@Override
	public String toString() {
		return String.format("%s", this.Size.toString());
	}
}
