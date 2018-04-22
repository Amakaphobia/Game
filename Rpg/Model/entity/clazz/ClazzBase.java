package entity.clazz;

import java.io.Serializable;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import entity.actorBase.ClassedActorBase;
import entity.basic.enums.skillsattributes.Skills;

@SuppressWarnings("javadoc")
public abstract class ClazzBase implements Serializable{

	// Entity Handling

	protected ClassedActorBase entity;

	public void registerEntity(ClassedActorBase sub) { this.entity = sub; }
	public void unregisterEntity(ClassedActorBase sub) {
		if(this.entity.equals(sub))
			this.entity = null;
	}

	protected final String hitDieCode;
	public String getHitDieCode() { return this.hitDieCode; }

	protected int level;
	public int getLevel() { return this.level; }
	public void levelUp(){
		this.level++;
		this.entity.onClassLevelUp(this);
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


}
