package common.skillcheck;

import dicemachine.DiceMachine;
import dicemachine.I_DiceMachine;
import entity.basic.attributeSet.Attributes;
import entity.basic.entityBase.SkilledEntityBase;
import entity.basic.skillSet.Skills;

public class AttributeCheck implements I_Check{

	private I_DiceMachine DiceMachine;
	
	private SkilledEntityBase Actor;
	private Attributes AttributeId;
	private int threshold;
	private int bonus;
	
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
