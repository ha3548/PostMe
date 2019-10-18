package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CommentDAO;
import model.ID;
import model.IdCo;
import model.Post;
import model.PostDAO;
import model.ProfileID;

public class PostController implements Initializable {

	PostDAO postdao = new PostDAO();
	Post post = new Post();
	Post post2 = new Post();
	Post post3 = new Post();
	
	CommentDAO commentdao = new CommentDAO();

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	
	
	@FXML
	private Label lbl_id1;
	@FXML
	private Label lbl_date;
	@FXML
	private Label lbl_content;

	@FXML
	private Label lbl_id2;
	@FXML
	private Label lbl_date2;
	@FXML
	private Label lbl_content2;

	@FXML
	private Label lbl_id3;
	@FXML
	private Label lbl_date3;
	@FXML
	private Label lbl_content3;

	@FXML
	private Button button_test1; // comment button
	@FXML
	private TextField comm1; // comment(comment content)
	@FXML
	private TextField nick1; // comment(comment nickname)
	@FXML
	private ListView<String> list_test; // comment list ListView

	private ObservableList<String> list_item; // comment list ListView

	@FXML
	private Button button_test2; // comment button
	@FXML
	private TextField comm2; // comment(comment content)
	@FXML
	private TextField nick2; // comment(comment nickname)
	@FXML
	private ListView<String> list_test2; // comment list ListView

	private ObservableList<String> list_item2; // comment list ListView

	@FXML
	private Button button_test3; // comment button
	@FXML
	private TextField comm3; // comment(comment content)
	@FXML
	private TextField nick3; // comment(comment nickname)
	@FXML
	private ListView<String> list_test3; // comment list ListView

	private ObservableList<String> list_item3; // comment list ListView

	@FXML
	private Label like_label; // like label

	@FXML
	private Label like_label2; // like label2

	@FXML
	private Label like_label3; // like label3


	@FXML
	private ImageView postImg1;
	@FXML
	private ImageView postImg2;
	@FXML
	private ImageView postImg3;

	@FXML
	private ImageView imageView; // like image (heart1)

	@FXML
	private ImageView imageView2; // like image (heart2) - full heart

	@FXML
	private ImageView imageView3; // like image (heart1)

	@FXML
	private ImageView imageView4; // like image (heart2) - full heart

	@FXML
	private ImageView imageView5; // like image (heart1)

	@FXML
	private ImageView imageView6; // like image (heart2) - full heart

	@FXML
	private void like_action1() { // like action1
		imageView.setVisible(false);
		imageView2.setVisible(true);
		postdao.updateLike(post);
		
		post.setLikes_cnt(postdao.getlikeCount(post.getPost_num()));
		System.out.println(Integer.toString(post.getLikes_cnt()));
		like_label.setText(Integer.toString(post.getLikes_cnt()));
	}

	@FXML
	private void like_action2() { // like action1
		postdao.updateLike(post);
		like_label.setText(Integer.toString(post.getLikes_cnt()));
	}

	@FXML
	private void like_action3() { // like action2
		imageView3.setVisible(false);
		imageView4.setVisible(true);
		postdao.updateLike(post2);
		
		post2.setLikes_cnt(postdao.getlikeCount(post2.getPost_num()));
		System.out.println(Integer.toString(post2.getLikes_cnt()));
		like_label2.setText(Integer.toString(post2.getLikes_cnt()));
	}

	@FXML
	private void like_action4() { // like action2
		postdao.updateLike(post2);
		like_label2.setText(Integer.toString(post2.getLikes_cnt()));
	}

	@FXML
	private void like_action5() { // like action3
		imageView5.setVisible(false);
		imageView6.setVisible(true);
		postdao.updateLike(post3);
		
		post3.setLikes_cnt(postdao.getlikeCount(post3.getPost_num()));
		System.out.println(Integer.toString(post3.getLikes_cnt()));
		like_label3.setText(Integer.toString(post3.getLikes_cnt()));
	}

	@FXML
	private void like_action6() { // like action3
		postdao.updateLike(post3);
		like_label3.setText(Integer.toString(post3.getLikes_cnt()));
	}

	@FXML
	private void addAction(ActionEvent action) { // comment write button

		
		commentdao.insertComment(post.getPost_num(), nick1.getText(), comm1.getText());
		list_item.add(nick1.getText() + "\t" + comm1.getText());
		System.out.print("닉네임" + "  ");
		//list_item.add(text_test.getText());
		System.out.println("댓글작성");
		comm1.clear();
	}

	@FXML
	private void addAction2(ActionEvent action) { // comment write button2
		
		commentdao.insertComment(post2.getPost_num(), nick2.getText(), comm2.getText());

		list_item2.add(nick2.getText() + "\t" + comm2.getText());
		System.out.print("닉네임" + "  ");
		System.out.println("댓글작성");
		comm2.clear();
	}

	@FXML
	private void addAction3(ActionEvent action) { // comment write button2
		
		commentdao.insertComment(post3.getPost_num(), nick3.getText(), comm3.getText());
		list_item3.add(nick3.getText() + "\t" + comm3.getText());
		System.out.print("닉네임" + "  ");
		System.out.println("댓글작성");
		comm3.clear();
	}

	@FXML
	private void new_post(ActionEvent event) throws IOException { // new post button
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/WriteView.fxml"));
		Scene second_scene = new Scene(second_parent, 800, 500);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();
	}

	@FXML
	private void edit_post(ActionEvent event) throws IOException { // edit post button
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/EditView.fxml"));
		Scene second_scene = new Scene(second_parent, 800, 500);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();
	}

	@FXML
	private void gotoMyPage(ActionEvent event) throws IOException { // profile button
		ProfileID.id = ID.id;
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/ProfileView.fxml"));
		Scene second_scene = new Scene(second_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();

	}

	@FXML
	private void input_comment() {

	}

	@FXML
	private void input_comment2() {

	}

	@FXML
	private void input_comment3() {

	}

	@FXML
	private void go_profile1(MouseEvent event) throws IOException { // 占쏙옙占� 占쌜쇽옙 占쏙옙튼

		ProfileID.id = lbl_id1.getText();
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/ProfileView.fxml"));
		Scene second_scene = new Scene(second_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();

	}

	@FXML
	private void go_profile2(MouseEvent event) throws IOException { // 占쏙옙占� 占쌜쇽옙 占쏙옙튼

		ProfileID.id = lbl_id2.getText();
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/ProfileView.fxml"));
		Scene second_scene = new Scene(second_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();

	}

	@FXML
	private void go_profile3(MouseEvent event) throws IOException { // 占쏙옙占� 占쌜쇽옙 占쏙옙튼

		ProfileID.id = lbl_id3.getText();
		Parent second_parent = FXMLLoader.load(getClass().getResource("../view/ProfileView.fxml"));
		Scene second_scene = new Scene(second_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(second_scene);
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		ArrayList<Post> postlist = postdao.postView();
		
		Post defaultpost = new Post(0, null, "http://via.placeholder.com/350x430", 
				null, null, 0);
		for(int i = 0; i<3;i++) {
			if(i>=postlist.size()) {
				
				postlist.add(defaultpost);
			}
		}
		
		String id;
		String img_link;
		String text;
		String reg_date;
		int likes_cnt;
		
		
		post = postlist.get(0);
		id = post.getId();
		img_link = "file:" + post.getImg_link();
		text = post.getText();
		reg_date = String.valueOf(post.getReg_date());
		likes_cnt = post.getLikes_cnt();

		lbl_id1.setText(id);
		postImg1.setImage(new Image(img_link));
		like_label.setText(String.valueOf(likes_cnt));
		lbl_date.setText(reg_date);
		lbl_content.setText(text);
		
		post2 = postlist.get(1);
		id = post2.getId();
		img_link = "file:" + post2.getImg_link();
		text = post2.getText();
		reg_date = String.valueOf(post2.getReg_date());
		likes_cnt = post2.getLikes_cnt();

		lbl_id2.setText(id);
		postImg2.setImage(new Image(img_link));
		like_label2.setText(String.valueOf(likes_cnt));
		lbl_date2.setText(reg_date);
		lbl_content2.setText(text);
		
		post3 = postlist.get(2);
		id = post3.getId();
		img_link = "file:" + post3.getImg_link();
		text = post3.getText();
		reg_date = String.valueOf(post3.getReg_date());
		likes_cnt = post3.getLikes_cnt();

		lbl_id3.setText(id);
		postImg3.setImage(new Image(img_link));
		like_label3.setText(String.valueOf(likes_cnt));
		lbl_date3.setText(reg_date);
		lbl_content3.setText(text);
		

		list_item = FXCollections.observableArrayList("댓글목록"); // comment 1
		list_test.setItems(list_item);

		button_test1.setDisable(false);

		list_item2 = FXCollections.observableArrayList("댓글목록"); // comment 2
		list_test2.setItems(list_item2);

		button_test2.setDisable(false);

		list_item3 = FXCollections.observableArrayList("댓글목록"); // comment 3
		list_test3.setItems(list_item3);

		button_test3.setDisable(false);

		nick1.setText(ID.id);
		nick2.setText(ID.id);
		nick3.setText(ID.id);

		// 댓글창1 불러오기
		ArrayList<IdCo> comments = null;
		comments = commentdao.commentShow(post.getPost_num());

		for (int i = 0; i < comments.size(); i++) {
			System.out.println("comments사이즈" + comments.size());
			IdCo idco = comments.get(i);
			list_item.add(idco.getId() + "\t" + idco.getMent());
		}

		list_test.setItems(list_item);

		// 댓글창2 불러오기
		comments = commentdao.commentShow(post2.getPost_num());

		for (int i = 0; i < comments.size(); i++) {
			System.out.println("comments사이즈" + comments.size());
			IdCo idco = comments.get(i);
			list_item2.add(idco.getId() + "\t" + idco.getMent());
		}

		list_test2.setItems(list_item2);

		// 댓글창3 불러오기
		comments = commentdao.commentShow(post3.getPost_num());

		for (int i = 0; i < comments.size(); i++) {
			System.out.println("comments사이즈" + comments.size());
			IdCo idco = comments.get(i);
			list_item3.add(idco.getId() + "\t" + idco.getMent());
		}

		list_test3.setItems(list_item3);

		comm1.focusedProperty().addListener(new ChangeListener<Boolean>() { // comment 1
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (comm1.isFocused()) {
					button_test1.setDisable(false);

				}
			}
		});

		comm2.focusedProperty().addListener(new ChangeListener<Boolean>() { // comment 2
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (comm2.isFocused()) {
					button_test2.setDisable(false);

				}
			}
		});

		comm3.focusedProperty().addListener(new ChangeListener<Boolean>() { // comment 3
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (comm3.isFocused()) {
					button_test3.setDisable(false);
				}
			}
		});

	} // initialize

} // PostController