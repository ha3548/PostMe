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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;
import model.UserDAO;

public class Find_accountController implements Initializable {
	UserDAO userdao = new UserDAO();
	
	@FXML
	private TextField email;
	
	@FXML
	private Label id;
	
	@FXML
	private Label pw;
	
	@FXML
	private Button find_btn;
	
	@FXML
	private void findAction(ActionEvent event) throws IOException {
		User user = new User();
		user = userdao.findAccount(email.getText());
		if(user==null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PostMe");
			alert.setHeaderText("해당하는 email이 없습니다.");
			alert.setContentText("<postMe>이용을 원하시면 회원가입을 해주세요.");
			alert.show();
		}
		else {
		id.setText(user.getId());
		pw.setText(user.getPw());
		}
		
	}
	
	@FXML
	private void backtoLogin(ActionEvent event) throws IOException {
		Parent new_parent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Scene new_scene = new Scene(new_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new_scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
