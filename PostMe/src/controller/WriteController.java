package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ID;
import model.PostDAO;

public class WriteController implements Initializable {
	PostDAO postdao = new PostDAO();
	
	
	@FXML
	private ImageView imgView2;
	@FXML
	public Label post_num;
	@FXML
	public DatePicker datePicker;
	@FXML
	public TextArea txt_content;
	@FXML
	public Label lbl_dir;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			String url = "http://via.placeholder.com/350x430";
			Image img = new Image(url);
			imgView2.setFitHeight(350);
			imgView2.setFitWidth(430);
			imgView2.setImage(img);
			
			post_num.setText(String.valueOf(postdao.postCount()));

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ���� ����
	public void picture_Choose() {
		FileChooser fc = new FileChooser();
		fc.setTitle("사진 불러오기");
		fc.setInitialDirectory(new File("C:/"));
		ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
		ExtensionFilter txtType = new ExtensionFilter("text file", "*.txt", "*.doc");
		fc.getExtensionFilters().addAll(imgType, txtType);

		File selectedFile = fc.showOpenDialog(null);
		System.out.println(selectedFile);
		lbl_dir.setText(selectedFile.getPath());

		try {
			
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			Image img = new Image(bis);
			imgView2.setFitHeight(350);
			imgView2.setFitWidth(430);
			imgView2.setImage(img);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	@FXML
	private void write_post() {
		
		System.out.println("��¥: " + datePicker.getValue());
		System.out.println("����: " + txt_content.getText());
		System.out.println("�������: " + lbl_dir.getText());
		
		String id = ID.id;
		String img_link = lbl_dir.getText();
		String text = txt_content.getText();
		
		postdao.insertPost(id, img_link,text);
		
		//입력창초기화
		txt_content.clear();
		datePicker.setValue(null);
		lbl_dir.setText(null);
		String url = "http://via.placeholder.com/350x430";
		Image img = new Image(url);
		imgView2.setImage(img);
		
		
		
	}

	
	public void cancle_post(ActionEvent event) throws IOException {
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/PostView.fxml"));
		Scene second_scene = new Scene(second_parent, 800, 500);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();
	}
}
