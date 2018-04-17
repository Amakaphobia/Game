package common.render;

import javafx.scene.Parent;

/**
 * This Interface is implemented by everything that can be shown in a Infofenster. Its only method is
 * {@link I_InfoAble#getInfoView()} and it is used to generate the InfoView
 * @author Dave
 *
 */
@FunctionalInterface
public interface I_InfoAble
{
	/**
	 * This Method is used to generate a {@link Parent} that holds this types InfoView
	 * @return the InfoView
	 */
	public abstract Parent getInfoView();
}
