package common.map;

import javafx.beans.property.IntegerProperty;

import boxes.Pair;
import entity.basic.entityBase.MoveableEntityBase;

/**
 * This interface is implemented by all gamemap classes. it handles positioning of entities and moving them.
 * @author hdaiv_000
 *
 */
public interface I_GameMap {

	/**
	 * this method is used to test if a given {@link Pair} of {@link IntegerProperty}s is inside the boundary
	 * @param Location the {@link Pair} of {@link IntegerProperty}s you want to test
	 * @return true if tis inside false if not
	 */
	public default boolean testBoundary(Pair<IntegerProperty, IntegerProperty> Location) {
		return this.testBoundaryX(Location.getKey().get()) && this.testBoundaryY(Location.getValue().get());
	}

	/**
	 * tests if a {@link MoveableEntityBase} is inside the maps boundaries
	 * @param Entity the Entity you want to test
	 * @return true if the entity is inside the boundary
	 */
	public default boolean testBoundary(MoveableEntityBase Entity) {
		return this.testBoundary(Entity.getLocation());
	}

	/**
	 * this method is used to check if a given x-coordinate is inside the map.
	 * @param coordX the x-position you want to check
	 * @return true if its inside false if not
	 */
	public abstract boolean testBoundaryX(int coordX);
	/**
	 * this method is used to check if a given y-coordinate is inside the map.
	 * @param coordY the y-position you want to check
	 * @return true if its inside false if not
	 */
	public abstract boolean testBoundaryY(int coordY);
}
