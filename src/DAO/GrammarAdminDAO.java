package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;

/*import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;*/

import BEAN.Grammar;

public class GrammarAdminDAO {
	
	public static List<Grammar> Displaysgrammarguidelinemanage (HttpServletRequest request,int start, int count,Connection conn)
	{
		List<Grammar> list = new ArrayList<Grammar>();
		
		//String sql = "select * from grammarGuideline ";/*limit "+(start-1)+", "+count+"";*/
		String sql="SELECT * FROM grammarGuideline ORDER BY grammarGuidelineId OFFSET "+(start-1)+" ROWS FETCH NEXT "+count+" ROWS ONLY";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					Grammar gmgl = new Grammar();
					
					int grammarguidelineid = rs.getInt("grammarGuidelineId");
					String grammarname = rs.getString("grammarName");
					String grammarimage = rs.getString("grammarImage");
					String content = rs.getString("content");
					
					gmgl.setGrammarGuidelineId(grammarguidelineid);
					gmgl.setGrammarName(grammarname);
					gmgl.setGrammarImage(grammarimage);
					gmgl.setContent(content);
					
					list.add(gmgl);
				}
				
				request.setAttribute("msglistgrammarguidelinemanage","co" + list.size()+"ban ghi");
				System.out.println("co "+list.size()+" ban ghi");
			}
			else 
			{
				request.setAttribute("msglistgrammarguidelinemanage","khong co ban ghi nao trong ds");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
			System.out.println("asda");
		}
				
		return list;
	}
	
	//dem bang do co bao nhieu hang
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
	
	
	
	//them ten bai huong dan vao csdl
	public static boolean Insertgrammarguidelinename(HttpServletRequest request, Connection conn, Grammar grammarguideline)
	{
		PreparedStatement ptmt = null;
		
		String sql = "insert into grammarGuideline(grammarName) values (N'"+grammarguideline.getGrammarName()+"')";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			int check = ptmt.executeUpdate();
			
			if (check != 0)
			{
				return true;
			}
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
		}
		
		return false;	
	}
	
	//ham them file anh cua cac de thi trong phan huong dan hoc ngu phap
	
	public static String Uploadimagegrammerguideline(Connection conn, HttpServletRequest request,HttpServletResponse response,int grammarguidelineid) 
			throws ServletException, IOException, org.apache.commons.fileupload.FileUploadException 
	{
		String test = "";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		final String Address = context.getRealPath("Imageupload/");
	
		//final String Address = "F://";
		final int MaxMemorySize = 1024 * 1024 * 3; //3MB
		final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
		
		boolean isMultipart = org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart)
		{
			test = "Miss multipart/form-data trong form";
		}
		
		DefaultFileItemFactory factory = new DefaultFileItemFactory();		
		
		// Set factory constraints
		factory.setSizeThreshold(MaxMemorySize);

		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				
		// Create a new file upload handler
		org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(factory);
			
		// Set overall request size constraint
		upload.setSizeMax(MaxRequestSize);
		
		
		
		try 
		{
			// Parse the request
		//	List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
			List<FileItem> items = upload.parseRequest(request);
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
		//	
			while (iter.hasNext()) 
			{
			    FileItem item = iter.next();

			    if (!item.isFormField()) 
			    {
			    	 String fileName = item.getName();			    	 
			    	 //pathFile: vá»‹ trÃ­ mÃ  chÃºng ta muá»‘n upload file vÃ o
			    	 //gá»­i cho server			    	 
			    	String pathFile = Address + File.separator + fileName;		
			    	System.out.println(pathFile);
			    	File uploadedFile = new File(pathFile);			    				    	
			    	boolean kt = uploadedFile.exists();			    	 
			    	try 
			    	{			    		
		    		if (kt == true)
			    		{	    					    
			    			test = "file exited";
			    		}
			    		else
			    		{		    			
			    			item.write(uploadedFile);
			    			GrammarAdminDAO.InsertGrammarImage(request, conn,fileName, grammarguidelineid);
			    			test="Success";
			    		}
						
						
					} 
			    	catch (Exception e) 
			    	{

			    		
			    		test = e.getMessage();
					}   	 
			    } 
			    else 
			    {
			    	test = "Add file Error!";
			    }
			}
			
		} 
		catch (org.apache.commons.fileupload.FileUploadException e) 
		{
			test = e.getMessage();
		}
		
		return test;
	}
	
	//xuat id cua bai huong dan ngu phap
	public static int findGrammarId(HttpServletRequest request, Connection conn, Grammar grammarguideline)
	{
		int grammarguidelineid = 0;
		
		PreparedStatement ptmt = null;
		
		
		String sql = "select grammarGuidelineId from grammarGuideline where grammarName= N'"+grammarguideline.getGrammarName()+"'";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
					
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				grammarguidelineid = rs.getInt("grammarGuidelineId");		
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgrammarguidelineimage",e.getMessage());
		}
		
		return grammarguidelineid;
	}
	
	
	
	
		//update ten hinh cho bai huong dan dua theo id cua bai huong dan
		public static void InsertGrammarImage(HttpServletRequest request, Connection conn,String image,int grammarguidelineid)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update grammarGuideline set grammarImage=? where grammarGuidelineId="+grammarguidelineid;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
					
				ptmt.setString(1,image);
				
				ptmt.executeUpdate();
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
			}
		}
	
		//them noi dung vao bai huong dan
		public static boolean Insertgrammarguidelinecontent(HttpServletRequest request, Connection conn, Grammar grammarguideline,int id)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update grammarGuideline set content=? where grammarGuidelineId="+id;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				String content = grammarguideline.getContent();
				
				
				ptmt.setString(1,content);
				
				int kt = ptmt.executeUpdate();
				
				if (kt != 0)
				{
					return true;
				}
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				request.setAttribute("msggrammarguidelinecontent",e.getMessage());
			}
			
			return false;	
		}
		
		//xoa bai huong dan
		public static void Xoabaihdnguphap(Connection conn, int grammarguidelineid)
		{
			String sql = "delete from grammarGuideline where grammarGuidelineId="+grammarguidelineid;
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ptmt.executeUpdate();
				
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{

				e.printStackTrace();
			}
		}
		
		//xoa ma bai huong dan trong cmtgrammar
		public static void Xoamahdnguphaptrongcmtgrammar(Connection conn, int grammarguidelineid)
		{
			String sql = "delete from cmtgrammar where grammarguidelineid="+grammarguidelineid;
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ptmt.executeUpdate();
				
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{

				e.printStackTrace();
			}
		}


}
