package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.LoginController;
import helpers.DB;
import helpers.Hashing;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginController loginController = new LoginController();
        DB database = new DB();
        Hashing hash = new Hashing();
		String hashpassword = hash.generateHash(password);
        loginController.setUsername(username);
        loginController.setPassword(hashpassword);
		try {
            if (database.validate(loginController)) {
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect("Assets/html/Home.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("Assets/html/LoginFailedPage.html");
            }
        } catch (ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
    }
	
}
