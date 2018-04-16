package entity.basic.entity;

import java.io.Serializable;

import entity.render.I_InfoAble;
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
	
	protected final String generateId() {
		return ""; // TODO
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
}
