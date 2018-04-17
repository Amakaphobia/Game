package common.map;

import boxes.Pair;
import entity.basic.entityBase.MoveableEntityBase;
import javafx.beans.property.IntegerProperty;

/**
 * This interface is implemented by all gamemap classes. it handles positioning of entities and moveing them.
 * @author hdaiv_000
 *
 */
public interface I_GameMap {
	
	/**
	 * this method is used to test if a given {@link Pair} of {@link IntegerProperty}s is inside the boundary
	 * @param Location
	 * @return
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
	 * @param coordX the y-position you want to check
	 * @return true if its inside false if not
	 */
	public abstract boolean testBoundaryY(int coordY);
}
