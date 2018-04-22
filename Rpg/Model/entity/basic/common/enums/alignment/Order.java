package entity.basic.common.enums.alignment;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * This Enum represents an Actors alignment X-Axis. It ranges from chaotic to Lawful.
 * @author Dave
 *
 */
public enum Order {
	/**
	 * An actor doesnt care for laws
	 */
	CHAOTIC(-1, "Chaotic"),

	/**
	 * An actor is neutral
	 */
	NEUTRAL(0, "Neutral"),

	/**
	 * An actor cares for laws
	 */
	LAWFUL(1, "Lawful");

	/**holds the vector value of this object*/
	private int id;
	/**holds a String used for the  full alignment name*/
	private String nameId;

	/**
	 * Constructor
	 * @param id the vector
	 * @param nameId the name
	 */
	private Order(int id, String nameId) {
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
	public Order step(int i) {
		Order ret;
		try {
			ret = Order.valueOf(this.id + i);
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
	public static Order valueOf(int i) {
		return
			Stream.of(Order.values())
				.filter(o -> o.getId() == i)
				.findFirst()
				.orElseThrow(() ->
					new NoSuchElementException(String.format("The Requested Element: %s does not exist", i)));
	}



}
