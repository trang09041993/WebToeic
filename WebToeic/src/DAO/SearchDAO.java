package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Grammar;

public class SearchDAO {
	
	public static List<Grammar> Displayresult (HttpServletRequest request,Connection conn, String name1)
	{
		List<Grammar> list = new ArrayList<Grammar>();
		
		String sql = "select * from grammarGuideline where grammarName like '%"+name1+"%'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (!rs.isBeforeFirst())
			{
				request.setAttribute("ketqua","Không có kết quả");
			}
			else 
			{
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
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("ketqua",e.getMessage());
		}
		
		return list;
	}

}
