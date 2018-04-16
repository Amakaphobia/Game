package entity.basic.entityBase;

import event.I_Event;

/**
 * This interface will be implemented by every class that can be a target of a {@link I_Event}.
 * It is functional and its only method is a consumer that is used accept the event. In the implementation it is used
 * to access the events {@link I_Event#execute(I_EventTarget)} Method and insert <code>this</code> as its parameters.
 * @author Dave
 *
 */
@FunctionalInterface
public interface I_EventTarget
{
	/**
	 * This Method is used to handle {@link I_Event} that target <code>this</code>.
	 * @param event the event that should be executed.
	 */
	public abstract void handleEvent(I_Event<? extends I_EventTarget> event);
}
