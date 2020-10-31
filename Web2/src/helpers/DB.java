package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.LoginController;
import controllers.Usuario;

public class DB {
	private String URL = "jdbc:postgresql://ec2-54-235-192-146.compute-1.amazonaws.com:5432/d2gscge40dldla?sslmode=require";
	private String User = "ewypbmrplyfcnk";
	private String Pass = "91906dbb57acd49e826f6c62b7187030f2e82af1421252aa3631cc093341d657";
	private String dbdriver = "org.postgresql.Driver";
	
	public void driver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, User, Pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public String insert(Usuario user) {
		driver(dbdriver);
		Connection conn = getConnection();
		String result = "usuario registrado correctamente";
		String sql = "insert into public.user(username, email, password) values(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Registro fallido";
		}

		return result;
	}
	
	public boolean validate(LoginController LoginCon) throws ClassNotFoundException, SQLException {
        boolean status = false;
        
        	driver(dbdriver);
        	Connection conn = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement("select * from public.user where username = ? and password = ? ");
            preparedStatement.setString(1, LoginCon.getUsername());
            preparedStatement.setString(2, LoginCon.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            return status;
         }
	
}
