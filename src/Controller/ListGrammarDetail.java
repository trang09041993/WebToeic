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

import BEAN.Cmtgrammar;
import DAO.CommentgrammarDAO;
import DAO.ListGrammAdmin;
import DB.DBConnetion;

/**
 * Servlet implementation class ListGrammarDetail
 */
@WebServlet("/ListGrammarDetail")
public class ListGrammarDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ListGrammarDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String mabaihdnguphapstr = request.getParameter("mabaihdnguphap");
		
		int mabaihdnguphap = Integer.parseInt(mabaihdnguphapstr);
		
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String content = ListGrammAdmin.Displaygrammarcontent(conn, mabaihdnguphap);
		
		//xuat so binh luan cua bai viet
		int countrow = CommentgrammarDAO.Countrow(conn, mabaihdnguphap);
		
		request.setAttribute("mabaihdnguphap",mabaihdnguphap);
		request.setAttribute("grammarguidelinecontent",content);
		request.setAttribute("kitutrongdatabase","\n");
		request.setAttribute("kitutronghtml","<br/>");
		request.setAttribute("countrow",countrow);
		
		
		List<Cmtgrammar> list = ListGrammAdmin.Displaycmtgrammar(conn, mabaihdnguphap);
		
		request.setAttribute("listcommentgrammar",list);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/GrammDetail.jsp");
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
