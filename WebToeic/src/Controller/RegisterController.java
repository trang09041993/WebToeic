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

import BEAN.member;
import DAO.RegisterDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("View/Register.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}

		Connection conn;
		try {
			conn = DBConnetion.getSQLServerConnection();
			String name = request.getParameter("name");
			String membername = request.getParameter("memberName");
			String memberpass = request.getParameter("memberPass");
			
			member member = new member();
			member.setName(name);
			member.setMemberName(membername);
			member.setMemberPass(memberpass);
			
			boolean test = RegisterDAO.InsertAccount(request, conn, member);
			
			if (test)
			{
				request.setAttribute("msgregister","Register is OK");
				RequestDispatcher rd = request.getRequestDispatcher("RegisterForward");
				rd.forward(request,response);
				System.out.println("them thanh cong");
			}
			else 
			{
				request.setAttribute("msgregister","Register Error");
				RequestDispatcher rd = request.getRequestDispatcher("RegisterForward");
				rd.forward(request,response);
				System.out.println("them khong thanh cong");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("loi them tai khoan");
		}
		
		
		

	}

}






























