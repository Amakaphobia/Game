package entity.basic.actorBase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;

import boxes.Pair;
import common.map.I_GameMap;
import dicemachine.DiceMachine;
import dicemachine.I_DiceMachine;
import entity.basic.EntityBase;

public abstract class MoveableActorBase extends EntityBase {


	// Dicemachine
	protected I_DiceMachine DiceMachine = new DiceMachine();

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

	// TODO
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

	// inherited abstract stuff
	@Override
	public abstract Parent getInfoView();

}