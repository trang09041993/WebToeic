package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class AddGrammarController
 */
@WebServlet("/AddGrammarController")
public class AddGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddGrammarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String pageidstr = request.getParameter("pageid");
		
		
		int pageid = Integer.parseInt(pageidstr);
		int count = 5;
		
		
		if (pageid == 1)
		{
			
		}
		else 
		{
			pageid = pageid -1;
			pageid = pageid * count +1;
		}
		
		Connection conn=null;
		List<Grammar> list = new ArrayList<Grammar>();
		try {
			conn=DBConnetion.getSQLServerConnection();
			list=GrammarAdminDAO.Displaysgrammarguidelinemanage(request,pageid, count, conn);
			request.setAttribute("ListGrammarAdmin",list);
			System.out.println("ListGrammarAdmin");
			int sumrow = GrammarAdminDAO.Countrow(conn);
			int maxpageid= 0;
			
			if ((sumrow/count)%2==0)
			{
				maxpageid = (sumrow/count);
			}
			else
			{
				maxpageid = (sumrow/count)+1;
			}
			
			request.setAttribute("maxpageid",maxpageid);
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			conn.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("View/Admin/AddGrammarName.jsp");
		rd.forward(request, response);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}






























