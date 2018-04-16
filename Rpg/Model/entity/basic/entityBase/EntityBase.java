package entity.basic.entityBase;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import common.render.I_InfoAble;

/**
 * This class is used as a abstract Base Class for all Person-Classes.
 * It handles id, living state, name, info, rendering on the map and a Picture used by Dialogs and InfoPanels.
 * @author hdaiv_000
 *
 */
public abstract class EntityBase implements Serializable, I_InfoAble{

	// TODO ticking?

	/**serial*/
	protected static final long serialVersionUID = -8965100601145336638L;

	/**set this to true if the object ticks*/
	protected boolean living = false;
	/**
	 * used to access {@link EntityBase#living}
	 * @return the living value of this object. True if alive.
	 */
	public boolean isLiving() { return this.living; }
	/**
	 * used to set {@link EntityBase#living}
	 * @param value true if alive
	 */
	public void setLiving(boolean value) { this.living = value; }

	/**this StringProperty holds the unique ID of a entity*/
	protected final StringProperty id;
	/**
	 * this exposes the unique {@link EntityBase#id} to the outside
	 * @return the id Property
	 */
	public StringProperty idProperty() { return this.id; }
	/**
	 * this is used to access the value of {@link EntityBase#id}
	 * @return this objects id as a String
	 */
	public String getId() { return this.id.get(); }

	/**this StringProperty holds the name of the entity*/
	protected final StringProperty name;
	/**
	 * this is used to expose the Property {@link EntityBase#name}.
	 * @return the property {@link EntityBase#name}.
	 */
	public StringProperty nameProperty() { return this.name; }
	/**
	 * used to access the value of {@link EntityBase#name}.
	 * @return the name as a String.
	 */
	public String getName() { return this.name.get(); }

	/**this StringProperty contains the description*/
	protected final StringProperty description;
	/**
	 * this is used to expose the {@link EntityBase#description} property to the outside.
	 * @return the description Property of this object
	 */
	public StringProperty descriptionProperty() { return this.description; }
	/**
	 * used to get the value contained in the {@link EntityBase#description}.
	 * @return a string containing the description.
	 */
	public String getDescription() { return this.description.get(); }

	/**holds the path to the image*/
	protected String bildPath;
	/**the Image used for information*/
	protected Image bild = null;
	/**the Parent that is used to render stuff on the map*/
	protected Parent render = null;

	/**
	 * Constructor
	 * @param name the name you want the Entity to have
	 * @param bildPath the path to the info image
	 */
	public EntityBase(String name, String bildPath) {
		super();
		this.id = new SimpleStringProperty(this.generateId());
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty("");
		this.bildPath = bildPath;
	}

	// TODO mach besser
	/**holds the next ID to be used*/
	private static int nextID = 0;
	/**
	 * this method is used internally by this class to generate the next unique ID to be assigned to the
	 * {@link EntityBase#id}.
	 * @return a string containing the next id.
	 */
	private final String generateId() {
		return String.valueOf(nextID++);
	}

	/**
	 * this method is used to access the RenderAble object of this object. On first use it will use
	 * {@link EntityBase#buildRender()} to generate the Rendering
	 * @return a Parent to be placed on the map.
	 */
	public Parent getRender() {
		if(this.render == null)
			this.render = this.buildRender();
		return this.render;
	}

	/**
	 * this method is used to access the ImageObject of this object. On first use it will use
	 * {@link EntityBase#bildPath} to access the image.
	 * @return a FX Image for the info panel or dialog windows
	 */
	public Image getBild() {
		if(this.bild == null)
			this.bild = new Image(this.bildPath);
		return this.bild;
	}

	/**
	 * this method is used to internally to generate the Renderable object for placement on the map.
	 * @return a Parent for this object
	 */
	protected abstract Parent buildRender();

	@Override
	public abstract Parent getInfoView();

	// Object Methods:

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof EntityBase)) return false;

		EntityBase other = (EntityBase) obj;
		return this.getId().equals(other.getId());
	}
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	@Override
	public String toString() {
		return String.format("EntityBase: %s %s", this.getId(), this.getName());
	}
}