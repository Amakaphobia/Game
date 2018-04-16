package entity.basic.entity;

import java.io.Serializable;

import common.render.I_InfoAble;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.image.Image;

public abstract class EntityBase implements Serializable, I_InfoAble{
	
	/**serial*/
	protected static final long serialVersionUID = -8965100601145336638L;
	
	protected boolean living = false;
	public boolean isLiving() { return this.living; }
	public void setLiving(boolean value) { this.living = value; }
	
	protected final StringProperty id;
	public StringProperty idProperty() { return this.id; }
	public String getId() { return this.id.get(); }
	
	protected final StringProperty name;
	public StringProperty nameProperty() { return this.name; }
	public String getName() { return this.name.get(); }
	
	protected final StringProperty info;
	public StringProperty infoProperty() { return this.info; }
	public String getInfo() { return this.info.get(); }
	
	protected String bildPath;
	protected Image bild = null;
	protected Parent render = null;
	
	public EntityBase(String name, String bildPath) {
		super();
		this.id = new SimpleStringProperty(this.generateId());
		this.name = new SimpleStringProperty(name);
		this.info = new SimpleStringProperty("");
		this.bildPath = bildPath;
	}
	
	// TODO ??
	private static int nextID = 0;
	private final String generateId() {
		return String.valueOf(nextID++);
	}	
	
	public Parent getRender() {
		if(this.render == null)
			this.render = this.buildRender();
		return this.render;
	}
	
	public Image getBild() {
		if(this.bild == null) {
			this.bild = new Image(this.bildPath);
		}
		return this.bild; 
	}
	
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









