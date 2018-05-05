package entity.basic.traits;

import java.io.Serializable;

import entity.basic.EntityBase;

/**
 * This  Interface is used to define traits. Its Method {@link #applyTo(EntityBase)} is used to activate a given Trait
 * on a given Actor, the actor will receive a learn Trait event with this trait and call the applyTo Method and use
 * itself as the parameter. The {@link #removeFrom(EntityBase)} Method is used the same way.
 *
 * @author Dave
 *
 * @param <T> the baseType of the target for the trait
 */
public interface I_Trait<T extends EntityBase> extends Serializable{

	/**
	 * This Method is used to activate the trait on a given entity. All Configurations to the Target happens in here.
	 * @param traitTarget the target for this trait
	 */
	public abstract void applyTo(T traitTarget);

	/**
	 * This Method is used to remove this trait from a given entity. All Configurations to the Target happens in here.
	 * @param traitTarget the target for this trait
	 */
	public abstract void removeFrom(T traitTarget);

	/**
	 * this method is used to check if a trait can be applied to a given target.
	 * @param traitTarget the trait target you want to test.
	 * @return true if a target can have this trait
	 */
	public default boolean testTarget(T traitTarget) {
		return true;
	}
}
