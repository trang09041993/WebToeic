package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Cmtgrammar;
import BEAN.Grammar;

public class ListGrammAdmin {
	
	public static List<Grammar> Displaygrammarguideline(int start, int count, Connection conn)
	{
		List<Grammar> list = new ArrayList<Grammar>();
		
		/*String sql = "select * from grammarGuideline limit "+(start-1)+", "+count+"";*/
		/*String sql="select top "+ count+ " * from grammarGuideline";*/
		String sql="SELECT * FROM grammarGuideline ORDER BY grammarGuidelineId OFFSET "+(start-1)+" ROWS FETCH NEXT "+count+" ROWS ONLY";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Grammar grammarguideline = new Grammar();
				
				int grammarguidelineid = rs.getInt("grammarGuidelineId");
				String grammarname = rs.getString("grammarName");
				String grammarimage = rs.getString("grammarImage");
				
				grammarguideline.setGrammarGuidelineId(grammarguidelineid);
				grammarguideline.setGrammarName(grammarname);
				grammarguideline.setGrammarImage(grammarimage);
				
				list.add(grammarguideline);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		String sql = "select count(*) from grammarGuideline";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static String Displaygrammarcontent(Connection conn, int mabaihdnguphap)
	{
		String content = "";
		
		String sql = "select * from grammarGuideline where grammarGuidelineId="+mabaihdnguphap;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				content = rs.getString("content");			
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}	
		return content;
	}
	
	public static List<Cmtgrammar> Displaycmtgrammar (Connection conn, int grammarguidelineid)
	{
		List<Cmtgrammar> list = new ArrayList<Cmtgrammar>();
		
		String sql = "select * from cmtGrammar where grammarGuidelineId="+grammarguidelineid;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
			while (rs.next())
			{
				Cmtgrammar cmt = new Cmtgrammar();
				int memberid = rs.getInt("memberId");
				String cmtgrammarcontent = rs.getString("cmtGrammarContent");
				
				String name = CommentgrammarDAO.Namemember(conn,memberid);
				
				
				cmt.setCmtgrammarcontent(cmtgrammarcontent);
				cmt.setName(name);
				
				list.add(cmt);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	

}
