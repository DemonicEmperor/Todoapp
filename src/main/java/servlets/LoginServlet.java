package servlets;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import dao.ToDoDaoImpl;
import dao.ToDoIntf;
/**
 * Servlet implementation class LoginServelets
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		ServletContext context=getServletContext();
		
		// read email,pass from browser/form
		String email=request.getParameter("email").trim();		
		String pass=request.getParameter("pass").trim();
		
		boolean isValid=true;
		// fields not null validation
		if(email.length()==0 || pass.isEmpty()) {
			request.setAttribute("loginError", "Please fill Email/Pass");
			isValid=false;
		} else {
			// verify email & pass in DB
			ToDoIntf dao=ToDoDaoImpl.getInstance();
			int regId=dao.login(email, pass);
			if(regId==0) {
				request.setAttribute("loginError", "Email/Pass is wrong");
				isValid=false;
			} else {
				session.setAttribute("regId", regId);
				context.getRequestDispatcher("/ViewTasks.jsp").forward(request,  response);
			}// else
		}// else
		if(isValid==false) {
			context.getRequestDispatcher("/Login.jsp").forward(request, response);
		}// if	

	}

}
