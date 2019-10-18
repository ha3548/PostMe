package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.FriendDAO;
import model.ID;
import model.Profile;
import model.ProfileDAO;
import model.ProfileID;

public class ProfileController implements Initializable {
	ProfileDAO profiledao = null;
	Profile profile = null;
	FriendDAO frienddao = new FriendDAO();

	private ObservableList<String> friend_item;

	@FXML
	private TextField txt_friend;

	@FXML
	private ObservableList<String> list_item;

	@FXML
	private ImageView imgView;
	@FXML
	private TextField txt_name, txt_age, txt_pn, txt_intro;
	@FXML
	private Label lbl_name, lbl_age, lbl_pn, lbl_gender, lbl_intro, lbl_directory;
	@FXML
	private ComboBox<String> com_gender;
	@FXML
	private Spinner<Integer> spin_age;
	@FXML
	private ListView<String> list_friend;

	ObservableList<String> list = FXCollections.observableArrayList("남자", "여자");
	Spinner<Integer> spinner = new Spinner<Integer>();
	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 20);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			System.out.println("FXML Load Complete");

			profiledao = new ProfileDAO();
			profile = profiledao.profileSearch(ProfileID.id);

			String url = "";// 사진 url
			if (profile.getProfile_image().equals("")) {
				url = "http://via.placeholder.com/200x200"; // 기본이미지
			} else
				url = "file:" + profile.getProfile_image();
			Image img = new Image(url);
			imgView.setFitHeight(200);
			imgView.setFitWidth(200);
			imgView.setImage(img);

			lbl_name.setText(profile.getName());
			lbl_pn.setText(profile.getPhone());
			lbl_intro.setText(profile.getInformation());
			lbl_age.setText(String.valueOf(profile.getAge()));
			lbl_gender.setText(profile.getGender());
			lbl_directory.setText(profile.getProfile_image());

			com_gender.setItems(list);
			spin_age.setValueFactory(valueFactory);

			// 친구목록 리스트
			ArrayList<String> friends = null;
			friends = frienddao.FriendView();

			friend_item = FXCollections.observableArrayList();

			for (int i = 0; i < friends.size(); i++) {
				friend_item.add(friends.get(i));
			}

			list_friend.setItems(friend_item);
		} catch (Exception e) {
			System.out.println("실패");
		}

	}

	@FXML
	private void cancel_profile(ActionEvent event) throws IOException {
		Parent post_parent = FXMLLoader.load(getClass().getResource("../view/PostView.fxml"));
		Scene post_scene = new Scene(post_parent, 800, 500);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(post_scene);
		stage.show();

	}

	public void profile_Choose() {
		if (ProfileID.id.equals(ID.id)) {
			FileChooser fc = new FileChooser();
			fc.setTitle("프로필 사진 불러오기");
			fc.setInitialDirectory(new File("C:/"));
			ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
			fc.getExtensionFilters().addAll(imgType);
			File selectedFile = fc.showOpenDialog(null);
			if (selectedFile != null) {
				lbl_directory.setText(selectedFile.getPath());
			}

			try {
				FileInputStream fis = new FileInputStream(selectedFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				Image img = new Image(bis);
				imgView.setImage(img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PostMe");
			alert.setHeaderText("아이디 불일치");
			alert.setContentText("친구의 프로필을 수정할 수 없습니다!!!");
			alert.show();
		}

	}

	// ���� ����
	public void profile_Delete() {
		if (ProfileID.id.equals(ID.id)) {
			String url = "http://via.placeholder.com/200x200";
			Image img = new Image(url);
			imgView.setImage(img);
			lbl_directory.setText("http://via.placeholder.com/200x200");
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PostMe");
			alert.setHeaderText("아이디 불일치");
			alert.setContentText("친구의 프로필을 수정할 수 없습니다!!!");
			alert.show();
		}

	}

	// ������ �� ����
	public void addAction() { // 정보 수정

		if (ProfileID.id.equals(ID.id)) {
			if (txt_name.getText().equals("")) {
			} else {
				lbl_name.setText(txt_name.getText());
				profile.setName(lbl_name.getText());
			}
			// ����
			if (spin_age.getValue() == null) {
			} else {
				lbl_age.setText(spin_age.getValue().toString());
				profile.setAge(spin_age.getValue());
			}
			// �ڵ�����ȣ
			if (txt_pn.getText().equals("")) {
			} else {
				lbl_pn.setText(txt_pn.getText());
				profile.setPhone(lbl_pn.getText());
			}
			// ����
			if (com_gender.getValue() == null) {
			} else {
				lbl_gender.setText(com_gender.getValue());
				profile.setGender(lbl_gender.getText());
			}

			// �Ұ�
			if (txt_intro.getText().equals("")) {
			} else {
				lbl_intro.setText(txt_intro.getText());
				profile.setInformation(lbl_intro.getText());
			}

			profile.setProfile_image(lbl_directory.getText());

			profiledao.updateProfile(profile);

			txt_name.clear();
			spin_age.setValueFactory(valueFactory);
			txt_pn.clear();
			com_gender.setValue(null);
			txt_intro.clear();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PostMe");
			alert.setHeaderText("아이디 불일치");
			alert.setContentText("친구의 프로필을 수정할 수 없습니다!!!");
			alert.show();
		}

	}

	@FXML
	private void add_friend(ActionEvent action) {
		if (ProfileID.id.equals(ID.id)) {
			frienddao.insertFriend(ID.id, txt_friend.getText());

			ArrayList<String> friends = null;
			friends = frienddao.FriendView();

			friend_item = FXCollections.observableArrayList();

			for (int i = 0; i < friends.size(); i++) {
				friend_item.add(friends.get(i));
			}

			list_friend.setItems(friend_item);

			txt_friend.clear();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("PostMe");
			alert.setHeaderText("아이디 불일치");
			alert.setContentText("친구의 프로필을 수정할 수 없습니다!!!");
			alert.show();
		}

	}

	@FXML
	private void input_friend() {
		txt_friend.clear();
	}

}
