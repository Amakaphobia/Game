package entity.spellbook;

import java.io.Serializable;

import common.render.I_InfoAble;
import event.I_Event;
import event.I_EventTarget;

/**
 * BaseInterface for magic spells and ability effects. This interface extends {@link I_InfoAble} and
 * {@link Serializable}. Its abstract Method {@link #getSpell()} is used to generate an {@link I_Event}
 * which describes the SPell itself.
 *
 * @param <T> the type of the event target.
 *
 * @author Dave
 * @see I_Event
 * @see I_InfoAble
 */
public interface I_Spell<T extends I_EventTarget> extends Serializable, I_InfoAble {

	/**
	 * this method is used to generate the {@link I_Event} that describes the Spell Effect.
	 * @return Event containing the code for spell execution.
	 */
	public abstract I_Event<T> getSpell();
}
