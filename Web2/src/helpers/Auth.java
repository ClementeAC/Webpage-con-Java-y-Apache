package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Auth {

	public boolean authCheck(String username, String password) {
		DB database = new DB();
		String sql = "select username, password from public.user where username = ?";
		Hashing hash = new Hashing();
		String hashpassword = hash.generateHash(password);
		Connection conn = database.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			String orUsername = "", orPass = "";
			while (rs.next()) {
				orUsername = rs.getString("username");
				orPass = rs.getString("password");
			} // end while
			if (orUsername.equals(username) && orPass.equals(hashpassword)) {
				// do something
				return true;
			} else {
				// do something
			}
		} // end try
		catch (Exception e) {
		} // end catch
		return false;
	}

}
