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

import BEAN.Examination;
import BEAN.Readexercise;
import DAO.QuanlybtdocDAO;
import DB.DBConnetion;


@WebServlet("/Themtenbtdoc")
public class Themtenbtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themtenbtdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String readname = request.getParameter("readname");
		
		Readexercise readexercise = new Readexercise();
		readexercise.setReadname(readname);
		
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			boolean kt = QuanlybtdocDAO.Themtenbtdoc(request, conn, readexercise);
			
			if (kt)
			{
				int readexeriseid = QuanlybtdocDAO.Xuatmabtdoc(request, conn, readexercise);
				
				QuanlybtdocDAO.Kiemtracauhoibtdoc(request, conn, 0, readexeriseid);
				
				
				request.setAttribute("readexeriseid", readexeriseid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Themhinhbtdoc.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msgquanlydsbtdoc","Th�m kh�ng th�nh c�ng");
				RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msgquanlydsbtdoc",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);
		}
	}

}
