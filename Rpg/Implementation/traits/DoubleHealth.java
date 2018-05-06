package traits;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;

import dicemachine.DiceCodeBase;
import entity.actorBase.ClassedActorBase;
import entity.basic.attributeSet.AttributeSet;
import entity.basic.attributeSet.I_AttributeSet;
import entity.basic.common.enums.size.Sizes;
import entity.basic.race.RaceBase;
import entity.basic.traits.TraitBase;

@SuppressWarnings("javadoc") //TODO remove me
public class DoubleHealth extends TraitBase<ClassedActorBase> {

	public DoubleHealth() {
		super("DoubleHealth", "doubles Health");
	}

	@Override
	public void applyTo(ClassedActorBase traitTarget) {
		traitTarget.getHp().maxHealthPrivProperty().set(traitTarget.getHp().getMaxHealthPriv() * 2);
	}

	@Override
	public void removeFrom(ClassedActorBase traitTarget) {
		traitTarget.getHp().maxHealthPrivProperty().set(traitTarget.getHp().getMaxHealthPriv() / 2);
	}

	@Override
	public Parent getInfoView() {
		// TODO Auto-generated method stub
		return null;
	}



	public static void main(String[] args) {
		RaceBase rb = new RaceBase("", Sizes.COLOSSAL) {

			@Override
			protected I_AttributeSet buildRacialAttributes() {
				return new AttributeSet(0);
			}

			@Override
			protected List<TraitBase<?>> buildRacialTraits() {
				return new ArrayList<>();
			}

		};
		ClassedActorBase cab = new ClassedActorBase("","",null,rb,null,new AttributeSet(5),null,null) {

			@Override
			public Parent getInfoView() {
				return null;
			}

			@Override
			public Parent buildRender() {
				return null;
			}
		};

		cab.getHp().addHitDie(DiceCodeBase.flat(2));
		TraitBase<ClassedActorBase> t = new DoubleHealth();
		cab.addTrait(t);

		System.out.println(cab.getHp().getMaxHealthPriv());

		cab.removeTrait(t);

		System.out.println(cab.getHp().getMaxHealthPriv());
	}
}
