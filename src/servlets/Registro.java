package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.Usuario;
import helpers.DB;
import helpers.Hashing;

/**
 * Servlet implementation class Registro
 */
@WebServlet("name = "ServletRegistro",
        urlPatterns = {"/Servlet/Registro"}")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Hashing hash = new Hashing();
		String hashpassword = hash.generateHash(password);
		Usuario user = new Usuario(username, email, hashpassword);
		DB database = new DB();
		String result = database.insert(user);
		response.getWriter().print(result);
		response.sendRedirect("Assets/html/LoginPage.html");
	}

}
