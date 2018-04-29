package common;

/**
 * This generic class is used to hold a overwrite-able default value. It is used for everything that normally is not
 * changed but can be changed by magic(for example) for a finite amount of time. In that case the method
 * {@link #set(Object)} can be used to insert a new value that will be accessed if the {@link #get()} method is called.
 * if you want to unset the Overwritten value you can call {@link #clearOverwrite()}.
 *
 * @author Dave
 *
 * @param <T> the type of your value
 */
public class OverwriteableDefault<T> {
	//TODO TEST

	/**the default value*/
	private T Default;
	/**the value that overwrites the default value*/
	private T Overwrite = null;

	/**
	 * this Method is used to access the current value. That means it will return {@link #Default} if {@link #Overwrite}
	 * is null. If it is not null {@link #Overwrite} will be returned
	 * @return the current value
	 */
	public T get() {
		return this.Overwrite != null ? Overwrite : Default;
	}

	/**
	 * This method is used to set the {@link #Overwrite} value of this object
	 * @param Overwrite the value that overwrites the default value
	 */
	public void set(T Overwrite) { this.Overwrite = Overwrite; }

	/**this method is used to unset the overwrite value*/
	public void clearOverwrite() { this.Overwrite = null; }

	/**
	 * Constructor
	 * @param Default the default value
	 */
	public OverwriteableDefault(T Default) {
		super();
		this.Default = Default;
	}

	//Object

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof OverwriteableDefault)) return false;

		OverwriteableDefault<?> other = (OverwriteableDefault<?>) obj;

		return
			this.Default.equals(other.Default) &&
			this.Overwrite.equals(other.Overwrite);
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
