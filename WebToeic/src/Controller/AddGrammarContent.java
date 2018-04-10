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
 * Servlet implementation class ListGrammar
 */
@WebServlet("/AddGrammarContent")
public class AddGrammarContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public AddGrammarContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn;
		try {
			conn = DBConnetion.getSQLServerConnection();
			String content = request.getParameter("content");
			String grammarguidelineidstr = request.getParameter("grammarguidelineid");
			
			int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
			Grammar grammarguideline = new Grammar();
			
			grammarguideline.setContent(content);
					
			boolean check = GrammarAdminDAO.Insertgrammarguidelinecontent(request, conn, grammarguideline,grammarguidelineid);
			if (check)
			{
				RequestDispatcher rd = request.getRequestDispatcher("AddGrammarController");
				rd.forward(request,response);
			}
			else 
			{	
				request.setAttribute("msggrammarguidelinecontent","Thêm nội dung không thành công");
				request.setAttribute("grammarguidelineid",grammarguidelineid);
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertGrammarContent.jsp");
				rd.forward(request,response);	
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	}

}
