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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ID;
import model.ProfileDAO;
import model.User;
import model.UserDAO;

public class SignUpController implements Initializable {
	
	private UserDAO userDao = new UserDAO();
	private ProfileDAO profileDao = new ProfileDAO();
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField pw;
	
	@FXML
	private TextField email;
	
	@FXML
	private Button signUp_btn_big;
	
	@FXML
	private void signUpAction(ActionEvent event) throws IOException {
		
		String u_id = id.getText();
		ID.id = u_id;
		id.setText("");

		String u_pw = pw.getText();
		pw.setText("");

		String u_email = email.getText();
		pw.setText("");

		boolean joinSuccess = join(u_id, u_pw, u_email);
		if (joinSuccess) {

			try {
				Parent new_parent = FXMLLoader.load(getClass().getResource("../view/SignUpSuccess.fxml"));
				Scene new_scene = new Scene(new_parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new_scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}	
	
	public boolean join(String id, String pw, String email) {//회원가입 가능여부
		if (userDao.userSearch(id, pw) == null) { //회원가입 가능
			User user = new User();
			user.setId(id);
			user.setPw(pw);
			user.setEmail(email);
			userDao.joinUser(user);
			profileDao.createProfile(id); //id만 가지고있는  profile
			return true;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("중복된 ID");
			alert.setHeaderText("이미 있는 ID입니다.");
			alert.setContentText("새로운 ID를 입력해주세요.");
			alert.show();
			return false;
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
