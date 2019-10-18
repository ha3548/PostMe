package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {
	Connection conn = null;
	ResultSet rs = null;

	public ProfileDAO() {
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

	// 0.profile 추가
	public void createProfile(String id) {
		String SQL = "insert into profile (id) values (?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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

	// 1.profile 수정
	public int updateProfile(Profile profile) { // insert
		String SQL = "update profile set name =?, age = ?, " + "phone=?, gender=?, information=?, profile_image=? "
				+ "where id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, profile.getName());
			stmt.setInt(2, profile.getAge());
			stmt.setString(3, profile.getPhone());
			stmt.setString(4, profile.getGender());
			stmt.setString(5, profile.getInformation());
			stmt.setString(6, profile.getProfile_image());
			stmt.setString(7, profile.getId());
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

	// 2.profile 조회
	public Profile profileSearch(String id) { // select

		String SQL = "select * from profile where id = ?";
		PreparedStatement stmt = null;
		Profile profile = null;
		try {
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				profile = new Profile();
				profile.setId(rs.getString("id"));			
				profile.setName(rs.getString("name"));
				profile.setAge(rs.getInt("age"));
				profile.setPhone(rs.getString("phone"));
				profile.setGender(rs.getString("gender"));
				profile.setInformation(rs.getString("information"));
				profile.setProfile_image(rs.getString("profile_image"));
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
		return profile;

	}

}
