package common.check;

import dicemachine.DiceCodeBase;
import dicemachine.I_DiceCode;
import entity.actorBase.SkilledActorBase;
import entity.basic.common.enums.skillsattributes.Attributes;
import entity.basic.common.enums.skillsattributes.Skills;

/**
 * This is a basic implementation of the {@link I_Check} Interface. It provides basic utilities such as a default dice
 * , a {@link I_DiceCode} and the threshold.
 * Its abstract method {@link #computeBonus()} is how the computation of the bonus is defined.
 * The Die that will be used to evaluate the Check is by default set to <code>1d20</code> but can be changed via the
 * {@link #setDie(I_DiceCode)} method.
 * @author Dave
 *
 */
public abstract class CheckBase implements I_Check{
	/**the actor that is performing the check*/
	protected SkilledActorBase Actor;
	/**the threshold that is to be reached*/
	protected int threshold;
	/**the bonus or malus added to the check by circumstances*/
	protected int situationalBonus;
	/**holds the die identifier default is set to 1d20*/
	private I_DiceCode die = DiceCodeBase.roll(1, 20);
	/**
	 * this Method is used to change the dice used for this check
	 * @param die the die you want to use
	 */
	public void setDie(I_DiceCode die) {
		this.die = die;
	}

	/**
	 * Constructor
	 * @param Actor the actor that performs the check
	 * @param threshold the threshold that is given
	 * @param SituationalBonus the bonus that get added to the result by circumstances
	 */
	public CheckBase(SkilledActorBase Actor, int threshold, int SituationalBonus) {
		super();
		this.Actor = Actor;
		this.threshold = threshold;
		this.situationalBonus = SituationalBonus;
	}

	/**
	 * This Method is used to Provide a way to compute the bonus modifier(for {@link Skills} or {@link Attributes})
	 * for a given check
	 * @return the bonus as an int
	 */
	protected abstract int computeBonus();

	@Override
	public I_CheckResult doCheck() {

		this.die
			.addModifier(this.computeBonus())
			.addModifier(this.situationalBonus);

		int erg = this.die.getValue() - this.threshold;

		return new CheckResult(erg);
	}

}
