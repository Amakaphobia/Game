package entity.actorBase.container;

import entity.actorBase.ClassedActorBase;

/**
 * This Interface is implemented by all entities that have hp. It is used to declare eventmethod that are called when
 * the actors health drops to 0 for example.
 *
 * @see ClassedActorBase
 * @author Dave
 *
 */
public interface I_HasHp
{
	/**This Method will be called when an Actors Health drops to zero*/
	public abstract void onHealthZero();
	/**this Method will be called when an Actors Health is no longer zero*/
	public abstract void onHealthNoLongerZero();
	/**this default Method will be called when an actors Health reaches Maximum Value by default its empty*/
	public default void onHealthFull() {}
	/**this default Method will be called when an actors Health changesValue by default its empty*/
	public default void onHealthGeneralChange() {}
}
