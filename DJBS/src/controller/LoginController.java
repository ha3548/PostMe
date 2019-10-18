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
import model.User;
import model.UserDAO;

public class LoginController implements Initializable {
	private UserDAO userDao = new UserDAO();

	@FXML
	private TextField id;

	@FXML
	private TextField pw;

	@FXML
	private Button login_btn;

	@FXML
	private Button finding_id_btn;

	@FXML
	private Button finding_pw_btn;

	@FXML
	private Button signUp_btn;

	@FXML
	private void find_id(ActionEvent event) throws IOException {
		Parent new_parent = FXMLLoader.load(getClass().getResource("../view/Find_account.fxml"));
		Scene new_scene = new Scene(new_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new_scene);
		stage.show();
	}

	@FXML
	private void find_pw(ActionEvent event) throws IOException {
		Parent new_parent = FXMLLoader.load(getClass().getResource("../view/Find_account.fxml"));
		Scene new_scene = new Scene(new_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new_scene);
		stage.show();
	}

	@FXML
	private void loginAction(ActionEvent event) {

		String u_id = id.getText();	
		ID.id = u_id;
		id.setText("");

		String u_pw = pw.getText();
		pw.setText("");

		boolean loginSuccess = login(u_id, u_pw);
		
		//성공여부에 따라 화면 전환
		
		if(loginSuccess) {
			try {
				Parent profile = FXMLLoader.load(getClass().getResource("../view/PostView.fxml"));
				Scene scene = new Scene(profile);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			ID.id = ""; //id변수 초기화

	}
	
	@FXML
	private void gotoSignUp(ActionEvent event) throws IOException {
		Parent new_parent = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
		Scene new_scene = new Scene(new_parent);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new_scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	public boolean login(String id, String pw) {
		User user = userDao.userSearch(id, pw);
		if (user == null) { // 회원아님
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PostMe");
			alert.setHeaderText("회원이 아닙니다.");
			alert.setContentText("<postMe>이용을 원하시면 회원가입을 해주세요.");
			alert.show();
			return false;
		} else { // 회원임
			if (user.getPw().equals(pw)) { // 성공
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("PostMe");
				alert.setHeaderText("로그인 성공");
				alert.setContentText("로그인이 완료되었습니다.");
				alert.show();
				return true;
			} else { //비밀번호 틀림
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("PostMe");
				alert.setHeaderText("로그인 실패");
				alert.setContentText("비밀번호가 틀렸습니다.");
				alert.show();
				return false;
			}
		}

	}

}
