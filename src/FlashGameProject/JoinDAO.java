package FlashGameProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class JoinDAO {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "team_game";
	private static final String PASS = "system";

	public Connection getConn() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connection 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 로드 실패");
		} catch (SQLException e) {
			System.out.println("Connection 실패");
		} finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("con.close() 에러");
			}
		}
		return con;
	}

	public Vector getJoin() {
		Vector data = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String passwordre = rs.getString("passwordre");
				String email = rs.getString("email");
				String phone = rs.getString("phone");

				Vector row = new Vector();
				row.add(id);
				row.add(username);
				row.add(password);
				row.add(passwordre);
				row.add(email);
				row.add(phone);

				data.add(row);

			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
		}
		return data;
	}

	public boolean insertCustomer(JoinDTO dto) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			con = getConn();
			String sql = "insert into member values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getUsername());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getPasswordre());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			int r = pstmt.executeUpdate();

			if (r > 0) {
				System.out.println("가입 성공");
				ok = true;
			} else {
				System.out.println("가입 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("가입 오류");
		}
		return ok;
	}
}
