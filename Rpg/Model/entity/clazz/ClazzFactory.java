package entity.clazz;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import entity.actorBase.ClassedActorBase;
import entity.basic.common.enums.clazz.Clazzs;
import entity.spellbook.I_SpellStrategy;
import entity.spellbook.strategies.NonMagical;

@SuppressWarnings("javadoc") //TODO Docu
public class ClazzFactory {

	public ClazzFactory() {
		super();
	}

	public static final ClazzBase warriorMock(ClassedActorBase Parent) {
		return new WarriorMock(Parent);
	}

	//TODO remove mock
	private static class WarriorMock extends ClazzBase{

		public WarriorMock(ClassedActorBase Parent) {
			super(Clazzs.WARRIOR, Parent);
		}

		@Override
		public javafx.scene.Parent getInfoView() {
			return null;
		}

		@Override
		protected Map<Integer, Consumer<? super ClazzBase>> buildLevelStrategies() {
			return new HashMap<>();
		}

		@Override
		protected I_SpellStrategy injectSpellStrategy() {
			return new NonMagical();
		}

	}
}
