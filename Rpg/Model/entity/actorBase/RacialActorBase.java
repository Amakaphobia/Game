package entity.actorBase;

import java.util.HashSet;
import java.util.Set;

import common.OverwriteableDefault;
import common.map.I_GameMap;
import entity.basic.EntityBase;
import entity.basic.common.BodyInformation;
import entity.basic.common.enums.size.Sizes;
import entity.basic.race.RaceBase;
import entity.basic.traits.TraitBase;

/**
 * This Class handles the interaction between the Actor and its race.
 * @author Dave
 * @see RaceBase
 *
 */
public abstract class RacialActorBase extends MoveableActorBase {

	/**
	 * the race of this actor
	 * @see RaceBase
	 */
	protected RaceBase Race;
	/**@return the race*/
	public RaceBase getRace() { return this.Race; }
	/**@param race the race to set*/
	public void setRace(RaceBase race) { this.Race = race; }


	//Constructor

	/**
	 * Constructor
	 * @param name {@link EntityBase#nameProperty()}
	 * @param bildPath {@link EntityBase#getBild()}
	 * @param Map {@link MoveableActorBase#Map}
	 * @param Race the Race for this Actor
	 */
	public RacialActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race) {
		super(name, bildPath, Map);


		this.traits = new HashSet<>();

		this.Race = Race;
		Race.getRacialTraits().forEach(t -> this.addTrait(t));
		this.BodyInformation = new BodyInformation(new OverwriteableDefault<>(Race.getDefaultSize()));
		this.BodyInformation.setParent(this);


	}

	/**this set contains all traits an actor can have*/
	private final Set<TraitBase<?>> traits;

	/**@param Trait the trait you want to add to this actor*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addTrait(TraitBase Trait) {
		Trait.applyTo(this);
		this.traits.add(Trait);
	}

	/**@param Trait the trait you want to remove from this actor*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeTrait(TraitBase Trait) {
		if(! this.traits.contains(Trait)) return;

		Trait.removeFrom(this);
		this.traits.remove(Trait);
	}

	//Delegates

	//BodyInformation

	/**holds information about the actors body*/
	protected BodyInformation BodyInformation;
	/**
	 * @return the current size
	 * @see BodyInformation#getSize()
	 */
	public Sizes getSize() { return this.BodyInformation.getSize(); }
	/**
	 * @param Size Size the {@link Sizes} to set
	 * @see BodyInformation#setSize(Sizes)
	 */
	public void setSize(Sizes Size) { this.BodyInformation.setSize(Size); }
	/**
	 * @see BodyInformation#clearSize()
	 */
	public void clearSize() { this.BodyInformation.clearSize(); }


}
