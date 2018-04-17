package entity.clazz;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import entity.basic.entityBase.SkilledEntityBase;
import entity.basic.skillSet.Skills;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class ClazzBase implements Serializable{	
	
	/**serial*/
	private static final long serialVersionUID = -3385314080301102689L;

	protected final String hitDieCode;
	
	protected int level;
	public int getLevel() { return this.level; }
	public void levelUp(){
		this.level++;
		this.entity.updateMaxHealth(this.hitDieCode);
	}
	
	protected final StringProperty name;
	public StringProperty nameProperty() { return this.name; }
	public String getName() { return this.name.get(); }
	
	protected final List<Skills> classSkills;
	public List<Skills> getClassSkills() { return this.classSkills; }
	
	public ClazzBase(Clazzs id) {
		this.name = new SimpleStringProperty(id.getId());
		this.hitDieCode = id.getHitDieCode();
		this.classSkills = id.getClassSkills();
		this.level = 1;
	}
	
	// Entity Handling

	protected SkilledEntityBase entity;

	public void registerEntity(SkilledEntityBase sub) {
		this.entity = sub;
	}
	
	public void unregisterEntity(SkilledEntityBase sub) {
		if(this.entity.equals(sub))
			this.entity = null;
	}
}
