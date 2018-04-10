package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.member;

public class RegisterDAO {
	
	public static boolean InsertAccount(HttpServletRequest request, Connection conn, member member)
	{
		PreparedStatement ptmt = null;
		
		  String sql = "insert into member(name,memberName,memberPass,categoryMemberId) values (?,?,?,?)";
		
		try 
		
		{
			int categorymemberid = 1;
			String sql1 = "INSERT INTO member(name,memberName,memberPass,categoryMemberId)" +
			        "VALUES(N'"+member.getName()+"','"+member.getMemberName()+"','"+member.getMemberPass()+"','"+categorymemberid+"')";
			
			//System.out.println("RegisterDAO");
			ptmt = conn.prepareStatement(sql1);
			
			/*String name = member.getName();
			String membername = member.getMemberName();
			String memberpass = member.getMemberPass();
			System.out.println(name+membername+memberpass);*/
			
			/*ptmt.setString(1,name);
			ptmt.setString(2,membername);
			ptmt.setString(3,memberpass);
			ptmt.setInt(4,categorymemberid);*/
			
			int kt = ptmt.executeUpdate();
			
			if (kt != 0)
			{
				return true;
			}
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgregister",e.getMessage());
			e.printStackTrace();
			System.out.println("dang ki loi");
		}
		
		return false;
		
	}


}
