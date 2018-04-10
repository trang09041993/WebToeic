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

import BEAN.Examination;
import DAO.ExamManage;
import DB.DBConnetion;

/**
 * Servlet implementation class AddNameExam
 */
@WebServlet("/AddNameExam")
public class AddNameExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddNameExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String examinationame = request.getParameter("examinationame");
		
		Examination examination = new Examination();
		examination.setExaminationame(examinationame);
		
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			boolean kt =ExamManage.Themtendethi(request, conn, examination);
			
			if (kt)
			{
				int examinationid = ExamManage.Xuatmadethi(request, conn, examination);
				
				ExamManage.Kiemtracauhoidethi(request, conn,0, examinationid);
				
				request.setAttribute("examinationid", examinationid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddImageExam.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msgquanlydethi","Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("ExamDisplayAdmin");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msgquanlydethi",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("ExamDisplayAdmin");
			rd.forward(request,response);
		}
	}

}
