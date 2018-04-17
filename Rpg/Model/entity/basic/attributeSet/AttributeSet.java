package entity.basic.attributeSet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The class represents the Attribute values of an actor.</br>
 *
 * Values:</br>
 * {@link Attributes#STRENGTH}</br>
 * {@link Attributes#PERCEPTION}</br>
 * {@link Attributes#AGILITY}</br>
 * {@link Attributes#ENDURANCE}</br>
 * {@link Attributes#INTELLIGENCE}</br>
 * {@link Attributes#WISDOM}
 * @author Dave
 *
 */
public class AttributeSet implements I_AttributeSet, Iterable<Attribute>, Serializable {

	/**serial*/
	private static final long serialVersionUID = 3583786418526275761L;

	/**this map holds the {@link Attribute}s of this set. It is accessed by providing the correct {@link Attributes}*/
	private Map<Attributes, Attribute> attMap;

	/**
	 * Constructor with specified values for each attribute
	 * @param strength strength value
	 * @param perception perception value
	 * @param agility agility value
	 * @param endurance endurance value
	 * @param inteligence intelligence value
	 * @param wisdom wisdom value
	 */
	public AttributeSet(int strength, int perception, int agility, int endurance, int inteligence, int wisdom) {
		super();
		this.attMap = new HashMap<>();
		this.putAttribute(Attributes.STRENGTH, strength);
		this.putAttribute(Attributes.PERCEPTION, perception);
		this.putAttribute(Attributes.AGILITY, agility);
		this.putAttribute(Attributes.ENDURANCE, endurance);
		this.putAttribute(Attributes.INTELLIGENCE, inteligence);
		this.putAttribute(Attributes.WISDOM, wisdom);
	}

	/**
	 * Internal Method used to put elements in the Attribute map {@link AttributeSet#attMap}
	 * @param id the {@link Attributes} you want to put
	 * @param value the level of this {@link Attributes}
	 */
	private void putAttribute(Attributes id, int value) {
		this.attMap.put(id, new Attribute(id, value));
	}

	/**
	 * Constructor with one value that gets used by all attributes.
	 * See {@link #AttributeSet(int, int, int, int, int, int)}
	 * @param all the value for your attributes
	 */
	public AttributeSet(int all) {
		this(all, all, all, all, all, all);
	}

	/** constructor with 0 for all values*/
	public AttributeSet() {
		this(0,0,0,0,0,0);
	}

	//I_AttributesetMethods

	@Override
	public Attribute getAttribute(Attributes id) {
		return this.attMap.get(id);
	}

	//Iterable Methods + Iterator

	@SuppressWarnings("javadoc")
	private class Atributerator implements Iterator<Attribute>{
		private Attributes[] all = Attributes.values();
		private int current = 0;
		@Override
		public boolean hasNext() {
			return current < all.length;
		}
		@Override
		public Attribute next() {
			return attMap.get(all[current++]);
		}
	}

	@Override
	public Iterator<Attribute> iterator() {
		return new Atributerator();
	}

	//Object Methods

	@Override
	public String toString() {
		// TODO
		String ret = "";
		for(Attribute e : this)
			ret = ret.concat(e.toString()).concat(" ");
		return ret.trim();
	}
	@Override
	public int hashCode() {
		int erg = 0;
		int count = 1;
		int mult;
		for(Attribute e : this) {
			mult = 1;
			for(int i = 0; i < count; i++)
				mult *= e.hashCode();
			erg += mult;
			count++;
		}
		return erg;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof AttributeSet)) return false;

		AttributeSet other = (AttributeSet) obj;
		return this.getStrength() == other.getStrength() &&
				this.getPerception() == other.getPerception() &&
				this.getAgility() == other.getAgility() &&
				this.getEndurance() == other.getEndurance() &&
				this.getIntelligence() == other.getIntelligence() &&
				this.getWisdom() == other.getWisdom();
	}
}