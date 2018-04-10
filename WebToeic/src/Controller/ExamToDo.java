package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Answeruser;
import BEAN.Examinationquestion;
import BEAN.Result;
import DAO.DoExamDAO;
import DB.DBConnetion;

/**
 * Servlet implementation class ExamToDo
 */
@WebServlet("/ExamToDo")
public class ExamToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExamToDo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("sessionuser")!=null)
		{
			int memberid = (int) session.getAttribute("sessionmemberid");
			
			request.setAttribute("kitutrongdatabase","\n");
			request.setAttribute("kitutronghtml","<br/>");
			
			request.setAttribute("examinationid",examinationid);
			request.setAttribute("memberid",memberid);
			
			List<Examinationquestion> list = DoExamDAO.Hienthicauhoidethi(conn, examinationid);
			
			request.setAttribute("dscauhoi",list);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/TestExam.jsp");
			rd.forward(request,response);
			
		}
		else
		{
			request.setAttribute("msgloidnlambai","Đăng nhập trước khi làm bài thi");
			
			RequestDispatcher rd = request.getRequestDispatcher("ListExamForward?pageid=1");
			rd.forward(request,response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Connection conn=null;
		try {
			conn = DBConnetion.getSQLServerConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		String memberidstr = request.getParameter("memberid");
		int memberid = Integer.parseInt(memberidstr);
		
		
		int countrow = DoExamDAO.Demsocauhoidethi(conn, examinationid);
		
		List<Examinationquestion> listcorrectanswer = DoExamDAO.Xuatdapandungdethi(conn, examinationid);
		
		List<Answeruser> listansweruser = new ArrayList<Answeruser>();
		
		
		int socaudung=0;
		int socaudungphannghe=0;
		int socaudungphandoc=0;
		for (int i =1; i<= countrow; i++)
		{
			String answer = request.getParameter("ans["+i+"]");
			
			if (answer != null)
			{
				//luu danh sach dap an cua user
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer(answer);		
				listansweruser.add(au);
				
				String dapandung = DoExamDAO.Dapancua1cauhoi(conn, examinationid,i);
				
				if (i<=4)
				{
					if (answer.equals(dapandung))
					{
						socaudung++;
						socaudungphannghe++;
					}
				}
				else 
				{
					if (i>=5)
					{
						if (answer.equals(dapandung))
						{
							socaudung++;
							socaudungphandoc++;
						}
					}
				}
					

					
			}
			else
			{
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer("No answer");		
				listansweruser.add(au);
			}
				
			
		}
		
		
		//Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String  reportDate= df.format(today);
		
		
		int socausai = countrow - socaudung;
		
		
		Result result = new Result();
		
		result.setCorrectanswernum(socaudung);
		result.setIncorrectanswernum(socausai);
	//	result.setTime(date.toString());
		result.setTime(reportDate);
		result.setExaminationid(examinationid);
		result.setMemberid(memberid);
		result.setCorrectanswerlisten(socaudungphannghe);
		result.setCorrectanswerread(socaudungphandoc);
		
		DoExamDAO.Luuketquathi(conn, result);
		
		request.setAttribute("kitutrongdatabase","\n");
		request.setAttribute("kitutronghtml","<br/>");
		request.setAttribute("listcorrectanswer",listcorrectanswer);
		request.setAttribute("listansweruser",listansweruser);
		
		
	//	List<Result> list = DoExamDAO.Xuatketquathi(conn,date.toString(), examinationid, memberid);
		List<Result> list = DoExamDAO.Xuatketquathi(conn,reportDate, examinationid, memberid);
		request.setAttribute("ketquathi",list);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Result.jsp");
		rd.forward(request,response);
		
	}

}
