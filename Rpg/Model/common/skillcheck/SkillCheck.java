package common.skillcheck;

import java.util.function.Supplier;

import dicemachine.I_DiceMachine;
import dicemachine.DiceMachine;
import entity.basic.entityBase.SkilledEntityBase;
import entity.basic.skillSet.Skills;

public class SkillCheck implements I_Check {
	
	private I_DiceMachine DiceMachine;
	
	private SkilledEntityBase Actor;
	private Skills SkillId;
	private int threshold;
	private int bonus;
	
	public SkillCheck(SkilledEntityBase Actor, Skills SkillId, int threshold, int bonus) {
		super();
		this.Actor = Actor;
		this.SkillId = SkillId;
		this.threshold = threshold;
		this.bonus = bonus;
		this.DiceMachine = new DiceMachine();
	}
	
	@Override
	public I_CheckResult docheck() {
		final int skillLevel = this.Actor.getSkillLevel(SkillId);
		if(skillLevel == 0 && !SkillId.isUntrained())
			return new CheckResult(Integer.MIN_VALUE);
		
		final int derivedBonus = 
			this.bonus + 
			skillLevel + 
			this.Actor.getAttribute(SkillId.getMainAttribute()).getDerivedModifier();
		
		//TODO dicecode factory?
		
		StringBuilder strb = new StringBuilder("1d20");
		
		if(bonus >= 0)
			strb.append("+");
		
		 strb.append(derivedBonus); 
		
		int erg = this.DiceMachine.getRoll(strb.toString()) - threshold;
		
		return new CheckResult(erg);
	}

}
