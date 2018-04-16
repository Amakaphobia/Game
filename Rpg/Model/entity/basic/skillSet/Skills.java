package entity.basic.skillSet;

import java.io.Serializable;

import entity.basic.attributeSet.Attributes;

public enum Skills implements Serializable{
	EMPTY("", null),
	ACROBATIC("Acrobatic", Attributes.AGILITY),
	SWIMMING("Swimming", Attributes.STRENGTH);
	
	private Skills(String id, Attributes mainAttribute) {
		this.id = id;
		this.mainAttribute = mainAttribute;
	}
	
	private String id;
	private Attributes mainAttribute;
	
	public String getId() { return this.id; }
	public Attributes getMainAttribute() { return this.mainAttribute; }
}
