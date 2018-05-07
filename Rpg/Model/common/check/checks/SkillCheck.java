package common.check.checks;

import common.check.results.CheckResult;
import common.check.results.I_CheckResult;
import entity.actorBase.SkilledActorBase;
import entity.basic.common.enums.skillsattributes.Skills;

@SuppressWarnings("javadoc")
public class SkillCheck extends CheckBase {

	private Skills SkillId;
	private int skillLevel;

	public SkillCheck(SkilledActorBase Actor, Skills SkillId, int threshold, int bonus) {
		super(Actor, threshold, bonus);
		this.SkillId = SkillId;

		this.skillLevel =  this.Actor.getSkillLevel(SkillId);
	}

	@Override
	public I_CheckResult doCheck() {

		if(skillLevel == 0 && !SkillId.isUntrained())
			return CheckResult.lost();

		return super.doCheck();
	}

	@Override
	protected int computeBonus() {
		return this.situationalBonus +
				skillLevel +
				this.Actor.getAttribute(SkillId.getMainAttribute()).getDerivedModifier();

	}

}
