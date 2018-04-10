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
import javax.servlet.http.HttpSession;

import BEAN.member;
import DAO.LoginDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("View/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		String membername = request.getParameter("username");
		String memberpass = request.getParameter("pass");
		member member = new member();
		member.setMemberName(membername);
		member.setMemberPass(memberpass);
		
		String name = LoginDAO.Exportnamemember(request, conn, member);
		int memberid = LoginDAO.Exportmemberid(request, conn, member);
		String authentication = LoginDAO.Authenticationmember(request, conn, member);
		
		if (authentication.equals("success"))
		{
			int authorization = LoginDAO.Authorizationmember(request, conn, member);
			if (authorization==1)
			{
				 
				HttpSession session = request.getSession(true);
				
				session.setAttribute("sessionuser",name);
				session.setAttribute("sessionmemberid",memberid);
				
				RequestDispatcher rd = request.getRequestDispatcher("HomeForward");
				rd.forward(request,response);
			}
			else
			{
				if (authorization==2)
				{
					HttpSession session = request.getSession(true);
					
					session.setAttribute("sessionadmin",name);
					
					RequestDispatcher rd = request.getRequestDispatcher("AdminForward");
					rd.forward(request,response);
				}
				
			}
		}
		else 
		{
			if (authentication.equals("fail"))
			{
				request.setAttribute("msglogin","Try againt");
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request,response);
			}
			
		}

	}

}
