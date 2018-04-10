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

import DAO.VocabularyDAO;
import DB.DBConnetion;




@WebServlet("/Themndchudetuvung")
public class Themndchudetuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Themndchudetuvung() 
    {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String vocabularyguidelineid = request.getParameter("vocabularyguidelineid");
		request.setAttribute("vocabularyguidelineid",vocabularyguidelineid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themndchudetuvung.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		
		String test = VocabularyDAO.Uploadndchudetuvung(conn, request, response, vocabularyguidelineid);
		
		if (test.equals("Success"))
		{
			VocabularyDAO.Kiemtrandchudetuvung(request, conn,1,vocabularyguidelineid);
			
			RequestDispatcher rd = request.getRequestDispatcher("VocabularyDisplayAdmin?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemndchudetuvung",test);
			request.setAttribute("vocabularyguidelineid", vocabularyguidelineid);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themndchudetuvung.jsp");
			rd.forward(request,response);		 
		}
	}

}
