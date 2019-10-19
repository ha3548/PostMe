package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	Connection conn = null;
	ResultSet rs = null;

	public UserDAO() {
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
	
	//1.회원가입
	public int joinUser(User user) { //insert
		String SQL = "insert into users values (?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getPw());
			stmt.setString(3, user.getEmail());
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
	
	//2.유저 정보 일치 조회
	public User userSearch(String id, String pw) { //select

		String SQL = "select * from users where id = ?";
		PreparedStatement stmt = null;
		User user = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("pw"));
				user.setEmail(rs.getString("email"));
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
		return user;

	}
	//3.계정찾기
	
	public User findAccount(String email) { //select

		String SQL = "select * from users where email = ?";
		PreparedStatement stmt = null;
		User user = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("pw"));
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
		return user;

	}
	

}
