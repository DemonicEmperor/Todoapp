package servlets;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;



import beans.Register;
import dao.ToDoDaoImpl;
import dao.ToDoIntf;
/**
 * Servlet implementation class RegisterServelet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		// reading Register.html form data
		String fname=request.getParameter("fname").trim();		
		String lname=request.getParameter("lname").trim();		
		String email=request.getParameter("email").trim();
		String pass=request.getParameter("pass").trim();
		long mobile=Long.parseLong(request.getParameter("mobile").trim());
		String address=request.getParameter("address").trim();
		
		// storing data in Register bean
		Register reg=new Register(0,fname,lname,email,pass,mobile,address);
		
		ToDoIntf dao=ToDoDaoImpl.getInstance();
		int regId=dao.register(reg);
		if(regId>0)
			response.sendRedirect("./Login.jsp");
		else
			out.println("Registration Failed");

	}

}
