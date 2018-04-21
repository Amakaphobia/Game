package entity.basic.entityBase;

import javafx.scene.Parent;

import common.map.I_GameMap;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.entityBase.container.HealthPointContainer;
import entity.basic.skillSet.I_SkillSet;
import entity.clazz.ClazzBase;

@SuppressWarnings("javadoc")
public abstract class ClassedEntityBase extends SkilledEntityBase {

	/**this container holds the entities Hp*/
	protected final HealthPointContainer Hp = new HealthPointContainer();

	public ClassedEntityBase(
			String name, String bildPath,
			I_GameMap Map,
			I_SkillSet SkillSet, I_AttributeSet AttributeSet) {
		super(name, bildPath, Map, SkillSet, AttributeSet);
	}

	public void onClassLevelUp(ClazzBase clazz) {
		this.Hp.addHitDie(clazz.getHitDieCode());
	}

	@Override
	public abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();

}
