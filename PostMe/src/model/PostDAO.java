package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {
	Connection conn = null;
	ResultSet rs = null;

	public PostDAO() {
		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "ora_user";
			String DB_PW = "password";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 1.입력
	public int insertPost(String id, String img_link, String text) { // insert
		String SQL = "insert into posts values (?,?,?,?,sysdate,0)";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, postCount());
			stmt.setString(2, id);
			stmt.setString(3, img_link);
			stmt.setString(4, text);
			stmt.executeUpdate();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return -1;
	}

	// 2.수정
	public int updatePost(Post post) { // insert
		String SQL = "update posts set img_link =?, text = ? " + "where post_num = ?";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, post.getImg_link());
			stmt.setString(2, post.getText());
			stmt.setInt(3, post.getPost_num());
			stmt.executeUpdate();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return -1;
	}

	// 3.id검색에 따른 게시물리스트()
	public ArrayList<Post> postView(String id) {
		ArrayList<Post> postlist = new ArrayList<>();

		String SQL = "select * from posts where id = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setId(id);
				post.setImg_link(rs.getString("img_link"));
				post.setLikes_cnt(rs.getInt("likes_cnt"));
				post.setPost_num(rs.getInt("post_num"));
				post.setReg_date(rs.getDate("reg_date"));
				post.setText(rs.getString("text"));
				postlist.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				if (rs != null && !rs.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return postlist;
	}

	// 3-1. 초기 게시물 리스트
	public ArrayList<Post> postView() {
		ArrayList<Post> postlist = new ArrayList<>();

		String SQL = "select * from posts where id in (select fr_id from friends where id = ?) or id = ? order by reg_date desc";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, ID.id);
			stmt.setString(2, ID.id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("rsnext");
				Post post = new Post();
				post.setId(rs.getString("id"));
				post.setImg_link(rs.getString("img_link"));
				post.setLikes_cnt(rs.getInt("likes_cnt"));
				post.setPost_num(rs.getInt("post_num"));
				post.setReg_date(rs.getDate("reg_date"));
				post.setText(rs.getString("text"));
				postlist.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				if (rs != null && !rs.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(postlist.size());
		return postlist;

	}

	// 4.좋아요 수 업데이트
	public int updateLike(Post post) { // insert
		String SQL = "update posts set likes_cnt = likes_cnt + 1 " + "where post_num = ?";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, post.getPost_num());
			stmt.executeUpdate();

			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return -1;
	}

	// 2.게시글삭제
	public void commentDelete(int post_num) {
		String SQL = "delete from posts where post_num = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, post_num);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				if (rs != null && !rs.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//포스트 번호 주면 좋아요개수 알려주는 함수
	public int getlikeCount(int post_num) {
		String SQL = "select likes_cnt from posts where post_num = ?";
		PreparedStatement stmt = null;
		int cnt=0;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, post_num);
			rs = stmt.executeQuery();
			if (rs.next()) {
				cnt=rs.getInt("likes_cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				if (rs != null && !rs.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	
	// 게시물번호 주는 함수
	public int postCount() {
		String SQL = "select max(post_num) from posts";
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(SQL);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("max(post_num)") + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				if (rs != null && !rs.isClosed())
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
