package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.member;

public class LoginDAO {
	
	public static String Authenticationmember(HttpServletRequest request, Connection conn, member member)
	{
		PreparedStatement ptmt = null;
		
		String test = "fail";
		String sql = "select memberName,memberPass from member";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				String membername = rs.getString("memberName");
				String memberpass= rs.getString("memberPass");
				
				if (member.getMemberName().equals(membername) && member.getMemberPass().equals(memberpass))
				{
					test = "success";
				}				
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return test;
		
	}
	
	public static int Authorizationmember(HttpServletRequest request, Connection conn, member member)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select categoryMemberId from member where memberName='"+member.getMemberName()+"' AND memberPass='"+member.getMemberPass()+"'";
		int categorymemberid = 0;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				categorymemberid = rs.getInt("categoryMemberId");		
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return categorymemberid;
	}
	
	
	public static String Exportnamemember(HttpServletRequest request, Connection conn, member member)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select name from member where memberName='"+member.getMemberName()+"' AND memberpass='"+member.getMemberPass()+"'";
		String name = "";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				name = rs.getString("name");
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return name;
	}
	
	//xuat ma thanh vien theo username va password
	public static int Exportmemberid(HttpServletRequest request, Connection conn, member member)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select memberid from member where memberName='"+member.getMemberName()+"' AND memberpass='"+member.getMemberPass()+"'";
		int memberid = 0;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				memberid = rs.getInt("memberId");
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return memberid;
	}
	
	//
	


}
