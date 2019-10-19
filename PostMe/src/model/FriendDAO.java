package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendDAO {
	Connection conn = null;
	ResultSet rs = null;
	
	public FriendDAO() {
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

	// 1.친구추가
	public int insertFriend(String id, String fr_id) { // insert
		String SQL = "insert into Friends(id,fr_id) values (?,?)";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, id);
			stmt.setString(2, fr_id);
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

	// 2.친구삭제
	public void friendDelete(String id, String fr_id) {
		String SQL = "delete from friends where id = ?, fr_id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, id);
			stmt.setString(1, fr_id);
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

	// 3.친구리스트 조회(id기준)
	public ArrayList<String> FriendView() {

		String SQL = "select fr_id from friends where id = ?";
		PreparedStatement stmt = null;
		ArrayList<String> friendlist = new ArrayList<>();
		String nowid = ProfileID.id;
		System.out.println("Contest :" + conn);
		try {
			stmt = conn.prepareStatement(SQL);
			System.out.println(nowid);
			stmt.setString(1, nowid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String friend = rs.getString("fr_id");
				friendlist.add(friend);
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
		return friendlist;
	}

}
