package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExamManage;
import DB.DBConnetion;


@WebServlet("/AddImageExam")
public class AddImageExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddImageExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		String test = ExamManage.Themhinhdethi(conn, request, response, examinationid);
		if (test.equals("Success"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("ExamDisplayAdmin?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemhinhdethi",test);
			request.setAttribute("examinationid", examinationid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddImageExam.jsp");
			rd.forward(request,response);		 
		}
	}

}
