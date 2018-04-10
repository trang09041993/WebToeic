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

import BEAN.ListVocabulary;
import DAO.VocabularyGuideDAO;
import DB.DBConnetion;


@WebServlet("/Hienthidshdtuvung")
public class Hienthidshdtuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Hienthidshdtuvung() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			String pageidstr = request.getParameter("pageid");
			
			
			int pageid = Integer.parseInt(pageidstr);
			int count = 3;
			
			
			if (pageid == 1)
			{
				
			}
			else 
			{
				pageid = pageid -1;
				pageid = pageid * count +1;
			}
			
			
			Connection conn = DBConnetion.getSQLServerConnection();
			
			List<ListVocabulary> list = VocabularyGuideDAO.Hienthidstuvung(request, pageid, count, conn);

			
			int sumrow = VocabularyGuideDAO.Countrow(conn);
			
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
			
			request.setAttribute("danhsachhdtuvung",list);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgdschudetuvung",e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Dshdtuvung.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
