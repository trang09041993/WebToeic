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

import DAO.GrammarAdminDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class DeleteGrammarLession
 */
@WebServlet("/DeleteGrammarLession")
public class DeleteGrammarLession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteGrammarLession() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		try {
			conn = DBConnetion.getSQLServerConnection();
			String grammarguidelineidstr = request.getParameter("grammarguidelineid");
			int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
			GrammarAdminDAO.Xoabaihdnguphap(conn, grammarguidelineid);
			//GrammarAdminDAO.Xoamahdnguphaptrongcmtgrammar(conn, grammarguidelineid)
			
			//RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage?pageid=1");
			RequestDispatcher rd = request.getRequestDispatcher("AddGrammarController");
			rd.forward(request,response);	
			
			conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
