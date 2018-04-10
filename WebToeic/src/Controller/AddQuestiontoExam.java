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


@WebServlet("/AddQuestiontoExam")
public class AddQuestiontoExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AddQuestiontoExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String examinationid = request.getParameter("examinationid");
		request.setAttribute("examinationid",examinationid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddQuestionToExam.jsp");
		rd.forward(request,response);
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
		
		String test = ExamManage.Uploadcauhoidethi(conn, request, response, examinationid);
		
		if (test.equals("Success"))
		{
			ExamManage.Kiemtracauhoidethi(request, conn,1, examinationid); 
			
			RequestDispatcher rd = request.getRequestDispatcher("ExamDisplayAdmin?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemcauhoidethi",test);
			request.setAttribute("examinationid", examinationid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddQuestionToExam.jsp");
			rd.forward(request,response);		 
		}
	}

}
