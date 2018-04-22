package entity.basic.enums.alignment;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * This Enum represents an Actors alignment y-Axis. It ranges from good to evil.
 * @author Dave
 *
 */
public enum Goodness {

	/**
	 * An Evil actor
	 */
	EVIL(-1,"Evil"),

	/**
	 * An Neutral actor
	 */
	NEUTRAL(0, "Neutral"),

	/**
	 * An good actor
	 */
	GOOD(1, "Good");

	/**holds the vector value of this object*/
	private int id;
	/**holds a String used for the  full alignment name*/
	private String nameId;

	/**
	 * Constructor
	 * @param id the vector
	 * @param nameId the name
	 */
	private Goodness(int id, String nameId) {
		this.id=id;
		this.nameId = nameId;
	}

	/**
	 * used to access the id
	 * @return Integer between -1 and 1
	 */
	public int getId() { return this.id; }

	/**
	 * used to access the name
	 * @return the name
	 */
	public String getNameId() { return this.nameId; }

	/**
	 * used to shift the alignment by a step
	 * @param i the step size
	 * @return the new alignment
	 */
	public Goodness step(int i) {
		Goodness ret;
		try {
			ret = Goodness.valueOf(this.id + i);
		}catch(NoSuchElementException e) {
			ret = this;
		}
		return ret;
	}

	/**
	 * used to get the alignment by its vector
	 * @param i the vector
	 * @return the new alignment
	 */
	public static Goodness valueOf(int i) {
		return
			Stream.of(Goodness.values())
				.filter(o -> o.getId() == i)
				.findFirst()
				.orElseThrow(() ->
					new NoSuchElementException(String.format("The Requested Element: %s does not exist", i)));
	}

}
