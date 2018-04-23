package common.decorator;

/**
 * This Interface is Implemented by All decorator classes. It Provides support for adding and removing more of the
 * same and treating the group as a single thing
 * @author Dave
 *
 * @param <S> The Type of the Object Itself
 * @param <R> the ReturnValue of the getValue method
 */
public interface I_SimpleDecorator <S extends I_SimpleDecorator<S, R>, R> extends I_SimpleDecoratorContainer<S>, Iterable<S>{

	/**
	 * Used to access the decorator of this object
	 * @return the decorator
	 */
	public abstract S getDecorator();

	/**
	 * return the level of this I_SimpleDecorator as a R
	 * @return the level of this I_SimpleDecorator
	 */
	public abstract R getValue();


}
