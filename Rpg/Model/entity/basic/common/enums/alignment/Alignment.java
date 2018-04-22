package entity.basic.common.enums.alignment;

import java.util.stream.Stream;

import boxes.Pair;

/**
 * This enum represents the 9 classical entity alignments of dnd.
 * @author Dave
 *
 */
public enum Alignment {

	/**
	 * Core Concepts: Duty, fairness, honor, property, responsibility, right, truth, virtue, worthiness
	 */
	LAWFUL_GOOD(Order.LAWFUL, Goodness.GOOD),

	/**
	 * Core Concepts: Benevolence, charity, considerateness, goodness, humaneness, kindness, reason, right
	 */
	NEUTRAL_GOOD(Order.NEUTRAL, Goodness.GOOD),

	/**
	 * Core Concepts: Benevolence, charity, freedom, joy, kindness, mercy, warmth
	 */
	CHAOTIC_GOOD(Order.CHAOTIC, Goodness.GOOD),

	/**
	 * Core Concepts: Harmony, loyalty, order, organization, rank, rule, system, tradition, word
	 */
	LAWFUL_NEUTRAL(Order.LAWFUL, Goodness.NEUTRAL),

	/**
	 * Core Concepts: Balance, cycles, equality, harmony, impartiality, inevitability, nature, seasons
	 */
	NEUTRAL(Order.NEUTRAL, Goodness.NEUTRAL),

	/**
	 * Core Concepts: Capriciousness, fate, freedom, individuality, liberty, self-possession, unpredictability
	 */
	CHAOTIC_NEUTRAL(Order.CHAOTIC, Goodness.NEUTRAL),

	/**
	 * Core Concepts: Calculation, discipline, malevolence, might, punishment, rationality, subjugation, terror
	 */
	LAWFUL_EVIL(Order.LAWFUL, Goodness.EVIL),

	/**
	 * Core Concepts: Desire, immorality, need, selfishness, sin, vice, viciousness, vileness, wickedness
	 */
	NEUTRAL_EVIL(Order.NEUTRAL, Goodness.EVIL),

	/**
	 * Core Concepts: Anarchy, anger, amorality, brutality, chaos, degeneracy, freedom, profaneness, violence
	 */
	CHAOTIC_EVIL(Order.CHAOTIC, Goodness.EVIL);

	/**this alignments order part*/
	private Order order;
	/**this alignments goodness part*/
	private Goodness goodness;

	/**
	 * Constructor
	 * @param order this alignments order part
	 * @param goodness this alignments goodness part
	 */
	private Alignment(Order order, Goodness goodness) {
		this.order = order;
		this.goodness = goodness;
	}

	/**
	 * This Method is used to access a vector that points to this alignment
	 * @return a {@link Pair} containing {@link Order#getId()} and {@link Goodness#getId()}
	 */
	public Pair<Integer, Integer> getVector(){ return new Pair<>(this.order.getId(), this.goodness.getId()); }
	/**
	 * Method used to access the order
	 * @return the order of this alignment
	 */
	public Order getOrder() { return this.order; }
	/**
	 * Method used to access the Goodness
	 * @return the Goodness of this alignment
	 */
	public Goodness getGoodness() { return this.goodness; }

	/**
	 * Used to shift this alignment to another one
	 * @param order how you want to manipulate the order vector
	 * @param goodness how you want to manipulate the goodness vector
	 * @return the new Alignment
	 */
	public Alignment shift(int order, int goodness) {
		return Stream.of(Alignment.values())
			.filter(a -> a.order == this.order.step(order))
			.filter(a -> a.goodness == this.goodness.step(goodness))
			.findFirst()
			.get();
	}

	/**
	 * Gets the classic Name of this alignment
	 * @return the name as a string
	 */
	@Override
	public String toString() {
		return order.getNameId().equals(goodness.getNameId()) ?
				order.getNameId() :
				String.format("%s-%s", order.getNameId(), goodness.getNameId());
	}
}
