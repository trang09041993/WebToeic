package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.VocabularyContent;
import DAO.VocabularyGuideDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class VocabularyContentDisplay
 */
@WebServlet("/VocabularyContentDisplay")
public class VocabularyContentDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VocabularyContentDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<VocabularyContent> list = VocabularyGuideDAO.Hienthinoidungtuvung(request, conn,vocabularyguidelineid);
		
		
		request.setAttribute("noidungtuvung",list);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Noidunghdtuvung.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
