package common.check;

import entity.basic.attributeSet.Attributes;
import entity.basic.entityBase.SkilledEntityBase;

/**
 * This Class is a implementation of the {@link I_Check} Interface.
 * It is used for Attribute Checks.
 * @author Dave
 *
 */
public class AttributeCheck extends CheckBase {

	/**the attribute that is checked*/
	private Attributes AttributeId;

	/**
	 * Constructor
	 * @param Actor the actor that performs the check
	 * @param AttributeId the attribute that is checked
	 * @param threshold the threshold that is given
	 * @param bonus the bonus that get added to the result
	 */
	public AttributeCheck(SkilledEntityBase Actor, Attributes AttributeId, int threshold, int bonus) {
		super(Actor, threshold, bonus);
		this.AttributeId = AttributeId;
	}

	@Override
	protected int computeBonus() {
		return this.Actor.getAttribute(this.AttributeId).getDerivedModifier() + this.bonus;
	}



}
