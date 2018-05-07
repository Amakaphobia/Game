package dicemachine;

/**
 * Use this after a critical hit in combat. it doubles the rolled damage
 * @author Dave
 *
 */
public class CrittedDamageDie extends DiceCodeRoll {

	/**
	 * Constructor
	 * @param diceCount the number of die
	 * @param diceSize the size of the dies
	 */
	public CrittedDamageDie(int diceCount, int diceSize) {
		super(diceCount, diceSize, false);
	}

	@Override
	protected void roll() {
		super.roll();
		this.rollValue *= 2;
	}
}
