package test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@SuppressWarnings("javadoc")
public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button b = new Button("Test");
		b.setOnAction(e -> System.out.println("HalloWelt"));

		Group g = new Group();
		g.getChildren().add(b);

		Scene sc = new Scene(g);
		primaryStage.setScene(sc);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();

	}

}
