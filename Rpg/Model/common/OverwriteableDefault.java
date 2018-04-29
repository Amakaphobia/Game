package common;


public class OverwriteableDefault<T> {
	//TODO TEST
	//TODO Docu

	private T Default;

	private T Overwrite = null;

	public T get() {
		return this.Overwrite != null ? Overwrite : Default;
	}

	public void set(T Size) { this.Overwrite = Size; }

	public void clearOverwrite() { this.Overwrite = null; }

	public OverwriteableDefault(T Default) {
		super();
		this.Default = Default;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof OverwriteableDefault)) return false;

		OverwriteableDefault<?> other = (OverwriteableDefault<?>) obj;

		return this.Default.equals(other.Default) && this.Overwrite.equals(other.Overwrite);
	}

	@Override
	public int hashCode() {
		return Default.hashCode() + Overwrite.hashCode() * Overwrite.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Current: %s\nDefault: %s", this.Default.toString(), this.Overwrite.toString());
	}

}
