package common.decorator;
/**
 * Super decorator interface for decorating objectcontainers
 * @author Dave
 *
 * @param <S> the objects type itself
 */
public interface I_SimpleDecoratorContainer<S> {
	/**
	 * this method is used to add a decorator to this Decorator
	 * @param other the Decorator you want to add
	 */
	public abstract void addDecorator(S other);
	/**
	 * this method is used to remove a decorator to this decorator
	 * @param other the decorator you want to remove
	 */
	public abstract void removeFirstDecorator(S other);
}
