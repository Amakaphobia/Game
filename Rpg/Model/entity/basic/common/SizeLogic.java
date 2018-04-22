package entity.basic.common;

import entity.basic.common.enums.size.Sizes;

@SuppressWarnings("javadoc") //TODO DOCU
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

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof SizeLogic)) return false;

		SizeLogic other = (SizeLogic) obj;

		return this.DefaultSize == other.DefaultSize && this.overwriteSize == other.overwriteSize;
	}

	@Override
	public int hashCode() {
		return DefaultSize.hashCode() + overwriteSize.hashCode() * overwriteSize.hashCode();
	}

	@Override
	public String toString() {
		return String.format("SizeLogic: %s (d): %s (o)", this.DefaultSize, this.overwriteSize);
	}
}
