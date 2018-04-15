package entity.attributeset;

import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
public class Attributeset implements I_Attributeset, Iterable<IntegerProperty> {

	/**holds the strength value*/
	private final IntegerProperty strength;
	/**holds the perception value*/
	private final IntegerProperty perception;
	/**holds the agility value*/
	private final IntegerProperty agility;
	/**holds the endurance value*/
	private final IntegerProperty endurance;
	/**holds the intelligence value*/
	private final IntegerProperty intelligence;
	/**holds the wisdom value*/
	private final IntegerProperty wisdom;

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
		this.strength = new SimpleIntegerProperty(strength);
		this.perception = new SimpleIntegerProperty(perception);
		this.agility = new SimpleIntegerProperty(agility);
		this.endurance = new SimpleIntegerProperty(endurance);
		this.intelligence = new SimpleIntegerProperty(inteligence);
		this.wisdom = new SimpleIntegerProperty(wisdom);
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
	public IntegerProperty strengthProperty() { return this.strength; }
	@Override
	public IntegerProperty perceptionProperty() { return this.perception; }
	@Override
	public IntegerProperty agilityProperty() { return this.agility; }
	@Override
	public IntegerProperty enduranceProperty() { return this.endurance; }
	@Override
	public IntegerProperty intelligenceProperty() { return this.intelligence; }
	@Override
	public IntegerProperty wisdomProperty() { return this.wisdom; }

	//Iterable Methods + Iterator

	// TODO
	@SuppressWarnings("javadoc")
	private class Skillterator implements Iterator<IntegerProperty>{
		private int current = 0;
		private final int max = 6;
		@Override
		public boolean hasNext() {
			return current < max;
		}

		// TODO
		@Override
		public IntegerProperty next() {
			IntegerProperty ret = null;
			switch(this.current) {
			case 0:
				ret = strength;
				break;
			case 1:
				ret = perception;
				break;
			case 2:
				ret = agility;
				break;
			case 3:
				ret = endurance;
				break;
			case 4:
				ret = intelligence;
				break;
			case 5:
				ret = wisdom;
			}
			this.current++;

			return ret;
		}

	}

	@Override
	public Iterator<IntegerProperty> iterator() {
		return new Skillterator();
	}

	//Object Methods
	// TODO
	@Override
	public String toString() {
		String ret = "";
		for(IntegerProperty e : this)
			ret = ret.concat(e.toString());
		return ret;
	}

	@Override
	public int hashCode() {
		int erg = 0;
		int count = 1;
		int mult;
		for(IntegerProperty e : this) {
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
