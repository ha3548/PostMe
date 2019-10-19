package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.ID;

public class SignUpSuccessController implements Initializable {

	@FXML
	private Label label1;
	
	@FXML
	private Label label2;
	
	@FXML
	private Button back_btn;
	
	@FXML
	private void backtoLogin(ActionEvent event) throws IOException {
		Parent new_parent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Scene new_scene = new Scene(new_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new_scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label1.setText(ID.id);
		
	}

}
