package entity.basic.attributeset;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.beans.property.IntegerProperty;

/**
 * The class represents the Attribute values of an actor.</br>
 *
 * Examples:</br>
 * <b>strength</b></br>
 * 	Defines how hard a actor can hit/how much he can carry</br>
 * <b>perception</b></br>
 * 	Defines how well a actor can hear/see.</br>
 * <b>agility</b></br>
 * 	Defines the speed of an actor and its ability to evade.</br>
 * <b>endurance</b></br>
 * 	Defines the health and general endurance of an actor.</br>
 * <b>intelligence</b></br>
 * 	Defines the booksmarts of a Actor.</br>
 * <b>wisdom</b></br>
 * 	Defines the streetsmarts of a Actor.</br>
 * @author Dave
 *
 */
public class Attributeset implements I_Attributeset, Iterable<Attribute> {

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
	public Attributeset(int strength, int perception, int agility, int endurance, int inteligence, int wisdom) {
		super();
		this.attMap = new HashMap<>();
		this.attMap.put(
			Attributes.STRENGTH,
			new Attribute(Attributes.STRENGTH, strength));
		this.attMap.put(
			Attributes.PERCEPTION,
			new Attribute(Attributes.PERCEPTION, perception));
		this.attMap.put(
			Attributes.AGILITY,
			new Attribute(Attributes.AGILITY, agility));
		this.attMap.put(
			Attributes.ENDURANCE,
			new Attribute(Attributes.ENDURANCE, endurance));
		this.attMap.put(
			Attributes.INTELLIGENCE,
			new Attribute(Attributes.INTELLIGENCE, inteligence));
		this.attMap.put(
			Attributes.WISDOM,
			new Attribute(Attributes.WISDOM, wisdom));
	}

	/**
	 * Constructor with one value that gets used by all attributes.
	 * See {@link #Attributeset(int, int, int, int, int, int)}
	 * @param all the value for your attributes
	 */
	public Attributeset(int all) {
		this(all, all, all, all, all, all);
	}

	/** constructor with 0 for all values*/
	public Attributeset() {
		this(0,0,0,0,0,0);
	}

	//I_SkillsetMethods

	@Override
	public IntegerProperty strengthProperty() {
		return this.attMap.get(Attributes.STRENGTH).valeProperty();
	}
	@Override
	public IntegerProperty perceptionProperty() {
		return this.attMap.get(Attributes.PERCEPTION).valeProperty();
	}
	@Override
	public IntegerProperty agilityProperty() {
		return this.attMap.get(Attributes.AGILITY).valeProperty();
	}
	@Override
	public IntegerProperty enduranceProperty() {
		return this.attMap.get(Attributes.ENDURANCE).valeProperty();
	}
	@Override
	public IntegerProperty intelligenceProperty() {
		return this.attMap.get(Attributes.INTELLIGENCE).valeProperty();
	}
	@Override
	public IntegerProperty wisdomProperty() {
		return this.attMap.get(Attributes.WISDOM).valeProperty();
	}

	//Iterable Methods + Iterator
	// TODO


	@Override
	public Iterator<Attribute> iterator() {
		return null;
	}

	//Object Methods
	// TODO
	@Override
	public String toString() {
		String ret = "";
		for(Attribute e : this)
			ret = ret.concat(e.toString());
		return ret;
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
		if(!(obj instanceof Attributeset)) return false;

		Attributeset other = (Attributeset) obj;
		return this.getStrength() == other.getStrength() &&
				this.getPerception() == other.getPerception() &&
				this.getAgility() == other.getAgility() &&
				this.getEndurance() == other.getEndurance() &&
				this.getIntelligence() == other.getIntelligence() &&
				this.getWisdom() == other.getWisdom();

	}

}
