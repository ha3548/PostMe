package application;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class FileChooserSample extends Application {

	String url = "";
	StackPane pane = new StackPane();
	Scene scene = new Scene(pane, 500, 500);

	@Override
	public void start(final Stage stage) {
		try {
			
		stage.setTitle("사진 불러오기");

		final FileChooser fileChooser = new FileChooser();
		final Button openButton = new Button("사진 선택");

		openButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {

				Image image = null; //이미지 주소 저장 변수
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(stage);
				
				if (file != null) {
					url = "file:" + file.getPath();
					System.out.println(url);
					image = new Image(url);
					
					ImageView iv = new ImageView();
					iv.setImage(image);
					
					pane.getChildren().add(iv);
					
					stage.setScene(scene);
					stage.show();
				}
	
			}
		});
		
		final GridPane inputGridPane = new GridPane();
		
		GridPane.setConstraints(openButton, 0, 1);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(openButton);
		
		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));
		
		
		
		stage.setScene(new Scene(rootGroup));
		stage.show();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}