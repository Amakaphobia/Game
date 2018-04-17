package entity.basic.skillSet;

import java.io.Serializable;

import entity.basic.attributeSet.Attributes;

/**
 * This Enum represents all learnable skills. It is used as Key.
 * @author Dave
 *
 */
public enum Skills implements Serializable{
	/**
	 * An empty Skill Dummy
	 */
	EMPTY("", null, false),

	/**
	 * Acrobatic is used to represent things like abseiling, jumping, general Acrobatic stuff
	 */
	ACROBATIC("Acrobatic", Attributes.AGILITY, false),

	/**
	 * This is used to move in water.
	 */
	SWIMMING("Swimming", Attributes.STRENGTH, true);

	/**
	 * Constructor
	 * @param id the Name of the Skill properly capitalized
	 * @param mainAttribute the main {@link Attributes} for this skill
	 */
	private Skills(String id, Attributes mainAttribute, boolean untrained) {
		this.id = id;
		this.mainAttribute = mainAttribute;
		this.untrained = untrained;
	}

	/**holds the Skills name*/
	private String id;
	/**the {@link Attributes} that effects this skill*/
	private Attributes mainAttribute;

	private boolean untrained;
	/**
	 * return the Skills name
	 * @return a String containing the name
	 */
	public String getId() { return this.id; }

	/**
	 * return the main {@link Attributes} for this skill
	 * @return the {@link Attributes} that effects this skill
	 */
	public Attributes getMainAttribute() { return this.mainAttribute; }
	
	public boolean isUntrained() { return this.untrained; }
}
