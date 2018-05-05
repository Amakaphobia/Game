package dicemachine;

public class DiceCodeFlat extends DiceCodeBase {

	private int flat;

	public DiceCodeFlat(int flat, boolean negative) {
		super(negative);
		this.flat = flat;
	}

	@Override
	public int get() {
		return
				this.negative ?
				-this.flat :
				 this.flat;
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();

		strb.append(this.get());

		if(this.hasDecorator()) {
			DiceCodeBase other = (DiceCodeBase) this.getDecorator();
			strb.append(other.negative ? "-" : "+")
				.append(other.toString());

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
		return Integer.hashCode(this.flat) + super.hashCode();
	}

	@Override
	protected int getMaxPersonal() {
		return this.get();
	}
}
