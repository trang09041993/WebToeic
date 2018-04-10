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
import DB.DBConnetion;


@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (request.getCharacterEncoding()!= null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		try 
		{
			String memberidstr = request.getParameter("memberid");
			String cmtgrammarcontent = request.getParameter("cmtgrammarcontent");
			String grammarguidelineidstr = request.getParameter("grammarguidelineid");
			
			int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
			int memberid = Integer.parseInt(memberidstr);
			
			Connection conn = DBConnetion.getSQLServerConnection();
			
			
			Cmtgrammar cmtgrammar = new Cmtgrammar();
			
			cmtgrammar.setCmtgrammarcontent(cmtgrammarcontent);
			cmtgrammar.setMemberid(memberid);
			cmtgrammar.setGrammarguidelineid(grammarguidelineid);
			
			CommentgrammarDAO.Insertcmtgrammar(request, conn, cmtgrammar);
			
			List<Cmtgrammar> list = CommentgrammarDAO.Displaycmtgrammar(conn, grammarguidelineid);
			
			request.setAttribute("listcommentgrammar",list);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/ListComment.jsp");
			rd.forward(request,response);
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
