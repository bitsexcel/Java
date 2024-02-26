package ch02;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ClickCounter extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	Button btn;
	Label lbl;
	int iClickCount = 0;

	@Override
	public void start(Stage primaryStage) {
		// Create the button
		btn = new Button();
		btn.setText("Click me please!");
		btn.setOnAction(e -> buttonClick());
		// Create Label
		lbl = new Label();
		lbl.setText("You have not clicked the button");
		// Add the button to a layout pane
		BorderPane pane = new BorderPane();
		pane.setTop(lbl);
		pane.setCenter(btn);
		// Add the layout pane to a scene
		Scene scene = new Scene(pane, 300, 250);
		// Finalize and show the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Click Me App");
		primaryStage.show();
	}

	public void buttonClick() {
		iClickCount++;
		if (iClickCount == 1) {
			lbl.setText("You have clicked once.");
		} else {
			lbl.setText("You have clicked "+ iClickCount + " times");
		}
	}
}
