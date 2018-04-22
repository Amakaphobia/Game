package entity.actorBase;

import common.map.I_GameMap;
import entity.basic.EntityBase;
import entity.basic.common.BodyInformation;
import entity.basic.common.SizeLogic;
import entity.basic.common.enums.size.Sizes;
import entity.basic.race.RaceBase;

@SuppressWarnings("javadoc") //TODO
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
		this.Race = Race;
		this.BodyInformation = new BodyInformation(new SizeLogic(Race.getDefaultSize()));
		this.BodyInformation.setParent(this);
	}

	protected void extractRaceEffects() {
		//TODO
	}

	//Delegates

	//BodyInformation

	/**holds information about the actors body*/
	protected BodyInformation BodyInformation;
	/**
	 * @return the current size
	 * @see entity.basic.common.BodyInformation#getSize()
	 */
	public Sizes getSize() { return this.BodyInformation.getSize(); }
	/**
	 * @param Size Size the {@link Sizes} to set
	 * @see entity.basic.common.BodyInformation#setSize(entity.basic.common.enums.size.Sizes)
	 */
	public void setSize(Sizes Size) { this.BodyInformation.setSize(Size); }
	/**
	 * @see entity.basic.common.BodyInformation#clearSize()
	 */
	public void clearSize() { this.BodyInformation.clearSize(); }


}
