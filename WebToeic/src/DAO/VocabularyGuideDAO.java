package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.fastinfoset.vocab.Vocabulary;

import BEAN.ListVocabulary;
import BEAN.VocabularyContent;

public class VocabularyGuideDAO {
	
	//hien thi danh sach de thi va phan trang
	public static List<ListVocabulary> Hienthidstuvung(HttpServletRequest request,int start, int count,Connection conn)
	{
		List<ListVocabulary> list = new ArrayList<ListVocabulary>();
		
		String sql = "select * from vocabularyGuideline ORDER BY vocabularyGuidelineId OFFSET "+(start-1)+" ROWS FETCH NEXT "+count+" ROWS ONLY";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					ListVocabulary vocabularyguideline = new ListVocabulary();
					
					int vocabularyguidelineid = rs.getInt("vocabularyGuidelineId");
					String vocabularyname = rs.getString("vocabularyName");
					String vocabularyimage = rs.getString("vocabularyImage");
					int checknoidung = rs.getInt("checknoidung");
					
					vocabularyguideline.setVocabularyguidelineid(vocabularyguidelineid);
					vocabularyguideline.setVocabularyname(vocabularyname);
					vocabularyguideline.setVocabularyimage(vocabularyimage);
					vocabularyguideline.setChecknoidung(checknoidung);
					
					list.add(vocabularyguideline);
				}
			}
			else 
			{
				request.setAttribute("msgdschudetuvung","Không có tiêu đề bài từ vựng nào");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgdschudetuvung",e.getMessage());
		}
				
		return list;
	}
	
	//dem so hang cua 1 bang
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		String sql = "select count(*) from vocabularyGuideline";
		
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
	
	//hien thi noi dung tu vung
	public static List<VocabularyContent> Hienthinoidungtuvung(HttpServletRequest request,Connection conn,int vocabularyguidelineid)
	{
		List<VocabularyContent> list = new ArrayList<VocabularyContent>();
		
		String sql = "select * from vocabularyContent where vocabularyGuidelineId="+vocabularyguidelineid;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					VocabularyContent vocabularycontent = new VocabularyContent();
					
					
					String vocabularycontentname = rs.getString("vocabularyContentName");
					String transcribe = rs.getString("transcribe");
					String image = rs.getString("image");
					String audiomp3 = rs.getString("audioMp3");
					String audiogg = rs.getString("audioGg");
					String mean = rs.getString("mean");
					
					vocabularycontent.setVocabularycontentname(vocabularycontentname);
					vocabularycontent.setTranscribe(transcribe);
					vocabularycontent.setImage(image);
					vocabularycontent.setAudiomp3(audiomp3);
					vocabularycontent.setAudiogg(audiogg);
					vocabularycontent.setMean(mean);
					
					
					list.add(vocabularycontent);
				}
			}
			else 
			{
				request.setAttribute("msgndchudetuvung","Không có nội dung nào");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgndchudetuvung",e.getMessage());
		}
				
		return list;
	}

}
