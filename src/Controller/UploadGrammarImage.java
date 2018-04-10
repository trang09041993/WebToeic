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

import org.apache.commons.fileupload.FileUploadException;

import DAO.GrammarAdminDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class UploadGrammarImage
 */
@WebServlet("/UploadGrammarImage")
public class UploadGrammarImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UploadGrammarImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Connection conn;
		try {
			conn = DBConnetion.getSQLServerConnection();
			String grammarguidelineidstr = request.getParameter("grammarguidelineid");
			int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
			
			String test = GrammarAdminDAO.Uploadimagegrammerguideline(conn, request, response,grammarguidelineid);
			if (test.equals("Success"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("AddGrammarController");
				rd.forward(request,response);	
			}
			else 
			{
				request.setAttribute("msgrammarguidelineimage",test);
				request.setAttribute("grammarguidelineid", grammarguidelineid);
		    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertGrammarImage.jsp");
				rd.forward(request,response);		 
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
