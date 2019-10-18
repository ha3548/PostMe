package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			
			
			ImageView iv = new ImageView();
			iv.setImage(image);
			
			pane.getChildren().add(iv);
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	Image image = new Image("file:C:\\Users\\Administrator\\Desktop\\다이어그램.PNG");
	
	StackPane pane = new StackPane();
	Scene scene = new Scene(pane,500,500);
	
	public static void main(String[] args) {
		launch(args);
	}
}
