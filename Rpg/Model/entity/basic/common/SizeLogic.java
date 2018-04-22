package entity.basic.common;

import entity.basic.common.enums.size.Sizes;

@SuppressWarnings("javadoc")
public class SizeLogic {

	private Sizes DefaultSize;

	private Sizes overwriteSize = null;

	public Sizes getSize() {
		return this.overwriteSize != null ? overwriteSize : DefaultSize;
	}

	public void setSize(Sizes Size) { this.overwriteSize = Size; }
	public void clearSize() { this.overwriteSize = null; }

	public SizeLogic(Sizes DefaultSize) {
		super();
		this.DefaultSize = DefaultSize;
	}

}
