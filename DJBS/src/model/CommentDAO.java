package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {
	Connection conn = null;
	ResultSet rs = null;

	public CommentDAO() {
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

	// 1.댓글입력
	public int insertComment(int post_num, String id, String ment) { // insert
		String SQL = "insert into comments values (?,?,?,?,sysdate)";

		PreparedStatement stmt = null;
		System.out.println(31);
		try {
			System.out.println(33);
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, commentCount());
			stmt.setInt(2, post_num);
			stmt.setString(3, id);
			stmt.setString(4, ment);
			System.out.println(post_num+" "+commentCount()+id+ment+"  확인");
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

	// 2.댓글삭제
	public void commentDelete(int com_num) {
		String SQL = "delete from comments where com_num = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, com_num);
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

	// 3.게시물별 댓글 출력 
	public ArrayList<IdCo> commentShow(int post_num) { // select

		String SQL = "select id, ment from comments where post_num = ?";
		PreparedStatement stmt = null;
		ArrayList<IdCo> postlist = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, post_num);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				IdCo idco = new IdCo(rs.getString("id"),rs.getString("ment"));
				postlist.add(idco);
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

	// 댓글 번호 주는 함수
	public int commentCount() {
		String SQL = "select max(com_num) from comments";
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(SQL);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("max(com_num)") + 1;
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

