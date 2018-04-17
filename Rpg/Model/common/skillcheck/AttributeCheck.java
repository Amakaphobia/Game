package common.skillcheck;

import dicemachine.DiceMachine;
import dicemachine.I_DiceMachine;
import entity.basic.attributeSet.Attributes;
import entity.basic.entityBase.SkilledEntityBase;

public class AttributeCheck implements I_Check{
	/**the {@link I_DiceMachine} used to generate the check result*/
	private I_DiceMachine DiceMachine;
	/**the actor that is performing the check*/
	private SkilledEntityBase Actor;
	/**the attribute that is checked*/
	private Attributes AttributeId;
	/**the threshold that is to be reached*/
	private int threshold;
	/**the bonus or malus added to the check*/
	private int bonus;
	
	/**
	 * Constructor
	 * @param Actor the actor that performs the check
	 * @param AttributeId the attribute that is checked
	 * @param threshold the threshold that is given
	 * @param bonus the bonus that get added to the result
	 */
	public AttributeCheck(SkilledEntityBase Actor, Attributes AttributeId, int threshold, int bonus) {
		super();
		this.Actor = Actor;
		this.AttributeId = AttributeId;
		this.threshold = threshold;
		this.bonus = bonus;
		this.DiceMachine = new DiceMachine();
	}

	@Override
	public I_CheckResult docheck() {
		
		final int derivedBonus = 
			this.bonus + 
			this.Actor.getAttribute(AttributeId).getDerivedModifier();
		
		//TODO dicecode factory?
		
		StringBuilder strb = new StringBuilder("1d20");
		
		if(bonus >= 0)
			strb.append("+");
		
		 strb.append(derivedBonus); 
		
		int erg = this.DiceMachine.getRoll(strb.toString()) - threshold;
		
		return new CheckResult(erg);
	}

}
