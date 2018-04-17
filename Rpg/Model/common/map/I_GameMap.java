package common.map;

import boxes.Pair;
import entity.basic.entity.MoveableEntityBase;
import javafx.beans.property.IntegerProperty;

public interface I_GameMap {
	public default boolean testBoundary(Pair<IntegerProperty, IntegerProperty> Location) {
		return this.testBoundaryX(Location.getKey().get()) && this.testBoundaryY(Location.getValue().get());
	}
	
	public default boolean testBoundary(MoveableEntityBase Entity) {
		return this.testBoundary(Entity.getLocation());
	}
	
	public abstract boolean testBoundaryX(int coordX);
	public abstract boolean testBoundaryY(int coordY);
}
