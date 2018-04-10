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

import BEAN.Grammar;
import DAO.GrammarAdminDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class InsertgrammarName
 */
@WebServlet("/InsertgrammarName")
public class InsertgrammarName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertgrammarName() {
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
		
		String grammarname = request.getParameter("grammarname");
		
		Grammar grammarguideline = new Grammar();
		grammarguideline.setGrammarName(grammarname);
		
		Connection conn;
		try {
			conn = DBConnetion.getSQLServerConnection();
			boolean check = GrammarAdminDAO.Insertgrammarguidelinename(request, conn, grammarguideline);
			
			if (check)
			{
				int grammarguidelineid = GrammarAdminDAO.findGrammarId(request, conn, grammarguideline);
				request.setAttribute("grammarguidelineid", grammarguidelineid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertGrammarImage.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msglistgrammarguidelinemanage","Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("AddGrammarController");
				rd.forward(request,response);
			}
			
			conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			
			request.setAttribute("msglistgrammarguidelinemanage",e1.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("AddGrammarController");
			rd.forward(request,response);
		}
	}

}
















