package entity.actorBase;

import common.map.I_GameMap;
import entity.basic.race.RaceBase;

@SuppressWarnings("javadoc")
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

	public RacialActorBase(
			String name, String bildPath,
			I_GameMap Map,
			RaceBase Race) {
		super(name, bildPath, Map);
		this.Race = Race;

	}

	protected void extractRaceEffects() {
		//TODO
	}
}
