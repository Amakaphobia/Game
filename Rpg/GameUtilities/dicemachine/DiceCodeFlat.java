package dicemachine;

/**
 * This Dice Code holds a flat modifier
 * @author Dave
 *
 * @see DiceCodeBase
 * @see I_DiceCode
 *
 */
public class DiceCodeFlat extends DiceCodeBase {

	/**holds the modifier*/
	private int flat;

	/**
	 * Hidden Constructor
	 * @param flat the value
	 */
	DiceCodeFlat(int flat) {
		super();
		this.flat = flat;
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();

		strb.append(this.getPersonal());

		if(this.hasDecorator()) {
			DiceCodeBase other = (DiceCodeBase) this.getDecorator();
			strb.append(other.toString());

		}

		return strb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof DiceCodeFlat)) return false;

		DiceCodeFlat other = (DiceCodeFlat)obj;
		return this.flat == other.flat
			&&  super.equals(other);
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(this.flat);
	}

	@Override
	protected int getMaxPersonal() {
		return this.getPersonal();
	}

	@Override
	protected int getPersonal() {
		return this.flat;
	}
}
