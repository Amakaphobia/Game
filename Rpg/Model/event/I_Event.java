package event;

import entity.basic.entityBase.I_EventTarget;

/**
 * This Interface is implemented by events (attacks, spells, natural catastrophes,...).
 * It is a functional Interface, and its only Consumer is the execute method. The Execute Method holds
 * the code that describes the Event and gets the Target as a Parameter.
 * @author Dave
 *
 * @param <T> The type of the Target
 */
@FunctionalInterface
public interface I_Event<T extends I_EventTarget>
{
	/**
	 * This Method holds the Content of the event. Every action that is a result of a Event and applies to the target
	 * is taken here.
	 * @param target the Target of the Event.
	 */
	public abstract void execute(T target);
}
