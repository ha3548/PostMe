package model;

import java.util.Date;

public class Post {
	private int post_num;
	private String id;
	private String img_link;
	private String text;
	private Date reg_date;
	private int likes_cnt;
	
	public Post() {
		
	}
	
	public Post(int post_num, String id, String img_link, String text, Date reg_date, int likes_cnt) {
		this.post_num = post_num;
		this.id = id;
		this.img_link = img_link;
		this.text = text;
		this.reg_date = reg_date;
		this.likes_cnt = likes_cnt;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImg_link() {
		return img_link;
	}
	public void setImg_link(String img_link) {
		this.img_link = img_link;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getLikes_cnt() {
		return likes_cnt;
	}
	public void setLikes_cnt(int likes_cnt) {
		this.likes_cnt = likes_cnt;
	}
}
