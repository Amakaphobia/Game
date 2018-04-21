package entity.basic.actorBase;

public interface I_HasHp
{
	public abstract void onHealthZero();
	public abstract void onHealthNoLongerZero();
	public default void onHealthFull() {}
	public default void onHealthGeneralChange() {}
}
