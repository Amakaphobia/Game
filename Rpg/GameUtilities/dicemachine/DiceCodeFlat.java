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

}
