package entity.actorBase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;

import boxes.Pair;
import common.map.I_GameMap;
import entity.basic.EntityBase;

@SuppressWarnings("javadoc")
public abstract class MoveableActorBase extends EntityBase {

	// Map
	protected I_GameMap Map;

	// Location

	protected Pair<IntegerProperty, IntegerProperty> Location =
			new Pair<>(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

	public Pair<IntegerProperty, IntegerProperty> getLocation() { return this.Location; }

	public IntegerProperty xProperty() { return this.Location.getKey(); }
	public IntegerProperty yProperty() { return this.Location.getValue(); }

	public int getX() { return this.xProperty().get(); }
	public int getY() { return this.yProperty().get(); }

	// TODO Test
	public void setX(int value) {
		if(this.Map.testBoundaryX(value))
			this.xProperty().set(value);
	}
	public void setY(int value) {
		if(this.Map.testBoundaryY(value))
			this.yProperty().set(value);
	}

	// Constructor

	public MoveableActorBase(String name, String bildPath, I_GameMap Map) {
		super(name, bildPath);
		this.Map = Map;
	}

	// Rendering

	protected Parent Render = null;

	public Parent getRender() {
		if(this.Render == null)
			this.Render = this.buildRender();
		return this.Render;
	}

	public abstract Parent buildRender();

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof MoveableActorBase)) return false;

		return super.equals(obj);
	}

}
