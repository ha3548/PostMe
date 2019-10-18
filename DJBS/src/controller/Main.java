package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
	
	@Override

	public void start(Stage stage) {

		this.stage = stage;
		this.stage.setTitle("Post Me");
		
		try {
			Parent login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(login);
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		launch(args);
	}
}