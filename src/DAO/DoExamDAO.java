package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.Result;

public class DoExamDAO {
	//hien thi danh sach de thi va phan trang
			public static List<Examination> Hienthidsdethi(HttpServletRequest request,int start, int count,Connection conn)
			{
				List<Examination> list = new ArrayList<Examination>();
				
				//String sql = "select * from examination limit "+(start-1)+", "+count+"";
				String sql="SELECT * FROM examination ORDER BY examinationId OFFSET "+(start-1)+" ROWS FETCH NEXT "+count+" ROWS ONLY";
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					if (rs.isBeforeFirst())
					{
						while (rs.next())
						{
							Examination examination = new Examination();
							
							int examinationid = rs.getInt("examinationId");
							String examinationame = rs.getString("examinationName");
							String examinatioimage = rs.getString("examinationImage");
							int checkedcauhoi = rs.getInt("checkedcauhoi");
							
							examination.setExaminationid(examinationid);
							examination.setExaminationame(examinationame);
							examination.setExaminatioimage(examinatioimage);
							examination.setCheckedcauhoi(checkedcauhoi);
							
							list.add(examination);
						}
					}
					else 
					{
						request.setAttribute("msgdsdethi","List emtry");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("msgdsdethi",e.getMessage());
				}
						
				return list;
			}
			
			//dem so hang cua 1 bang
			public static int Countrow(Connection conn)
			{
				int count = 0;
				
				
				String sql = "select count(*) from examination";
				
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
			
			//hien thi danh sach cau hoi de thi
			public static List<Examinationquestion> Hienthicauhoidethi(Connection conn,int examinationid)
			{
				List<Examinationquestion> list = new ArrayList<Examinationquestion>();
				 
				String sql = "select * from examinationQuestion where examinationId="+examinationid;
				
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							Examinationquestion ex = new Examinationquestion();
							
							int	examinationquestionid = rs.getInt("examinationQuestionId");
							int	num = rs.getInt("num");
							String imagequestion = rs.getString("imageQuestion");
							String audiogg = rs.getString("audioGg");
							String audiomp3 = rs.getString("audioMp3");
							String paragraph = rs.getString("paragraph");
							String question = rs.getString("question");
							String option1 = rs.getString("option1");
							String option2 = rs.getString("option2");
							String option3 = rs.getString("option3");
							String option4 = rs.getString("option4");
							String correctanswer = rs.getString("correctAnswer");	
							
							ex.setExaminationquestionid(examinationquestionid);
							ex.setNum(num);
							ex.setImagequestion(imagequestion);
							ex.setAudiogg(audiogg);
							ex.setAudiomp3(audiomp3);
							ex.setParagraph(paragraph);
							ex.setQuestion(question);
							ex.setOption1(option1);
							ex.setOption2(option2);
							ex.setOption3(option3);
							ex.setOption4(option4);
							ex.setCorrectanswer(correctanswer);
							ex.setExaminationid(examinationid);
							
							
							list.add(ex);
						}
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
						
				return list;
			}
			
			//xuat dap an dung cua cac cau hoi
			
			public static List<Examinationquestion> Xuatdapandungdethi(Connection conn,int examinationid)
			{
				List<Examinationquestion> list = new ArrayList<Examinationquestion>();
				 
				String sql = "select * from examinationQuestion where examinationId="+examinationid;
				
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							Examinationquestion ex = new Examinationquestion();
							
							int	examinationquestionid = rs.getInt("examinationQuestionId");
							int	num = rs.getInt("num");
							String imagequestion = rs.getString("imageQuestion");
							String audiogg = rs.getString("audioGg");
							String audiomp3 = rs.getString("audioMp3");
							String paragraph = rs.getString("paragraph");
							String question = rs.getString("question");
							String option1 = rs.getString("option1");
							String option2 = rs.getString("option2");
							String option3 = rs.getString("option3");
							String option4 = rs.getString("option4");
							String correctanswer = rs.getString("correctAnswer");	
							
							ex.setExaminationquestionid(examinationquestionid);
							ex.setNum(num);
							ex.setImagequestion(imagequestion);
							ex.setAudiogg(audiogg);
							ex.setAudiomp3(audiomp3);
							ex.setParagraph(paragraph);
							ex.setQuestion(question);
							ex.setOption1(option1);
							ex.setOption2(option2);
							ex.setOption3(option3);
							ex.setOption4(option4);
							ex.setCorrectanswer(correctanswer);
							ex.setExaminationid(examinationid);
							
							
							list.add(ex);
						}
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
						
				return list;
			}
			
					//dem so cau hoi cua 1 de thi
					public static int Demsocauhoidethi(Connection conn,int examinationid)
					{
						int count = 0;
						
						
						String sql = "select count(*) from examinationQuestion where examinationId="+examinationid;
						
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
			
			//them ket qua thi cua thanh vien vao trong bảng result
			
			public static void Luuketquathi(Connection conn, Result result)
			{
				PreparedStatement ptmt = null;
				
				
				String sql = "insert into result(correctAnswerNum,incorrectAnswerNum,times,examinationId,memberId,correctanswerread,correctanswerlisten) values (?,?,?,?,?,?,?)";
				
				try 
				{
					ptmt = conn.prepareStatement(sql);
					
					int correctanswernum = result.getCorrectanswernum();
					int incorrectanswernum = result.getIncorrectanswernum();
					String time = result.getTime().toString();
					int examinationid = result.getExaminationid();
					int memberid = result.getMemberid();
					int correctanswerread = result.getCorrectanswerread();
					int correctanswerlisten = result.getCorrectanswerlisten();
					
					
					
					ptmt.setInt(1,correctanswernum);
					ptmt.setInt(2,incorrectanswernum);
					ptmt.setString(3,time);
					ptmt.setInt(4,examinationid);
					ptmt.setInt(5,memberid);
					ptmt.setInt(6,correctanswerread);
					ptmt.setInt(7,correctanswerlisten);
					
					ptmt.executeUpdate();
					
					
					ptmt.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
			}
			
			//xuat dap an dung cua 1 cau hoi trong de thi
			public static String Dapancua1cauhoi(Connection conn,int examinationid, int num)
			{
				String dapan="";
				
				String sql = "select correctAnswer from examinationQuestion where examinationId="+examinationid+" and num="+num;
				
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							dapan = rs.getString(1);
						}
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				
				return dapan;
			}
			
					//xuat ket qua thi cua thanh vien
					public static List<Result> Xuatketquathi(Connection conn,String time,int examinationid,int memberid)
					{
						
						List<Result> list = new ArrayList<Result>();
						
						String sql = "select * from result where times='"+time+"' and examinationId="+examinationid+" and memberId="+memberid;
						
						try 
						{
							PreparedStatement ptmt = conn.prepareStatement(sql);
							
							ResultSet rs = ptmt.executeQuery();
							
							while(rs.next())
							{
								int correctanswernum = rs.getInt("correctAnswerNum");
								int incorrectanswernum = rs.getInt("incorrectAnswerNum");
								int correctanswerread = rs.getInt("correctanswerread");
								int correctanswerlisten = rs.getInt("correctanswerlisten");
								
								Result result = new Result();
								result.setCorrectanswernum(correctanswernum);
								result.setIncorrectanswernum(incorrectanswernum);
								result.setCorrectanswerlisten(correctanswerlisten);
								result.setCorrectanswerread(correctanswerread);
								
								
								list.add(result);
								
							}
							
								
						} 
						catch (SQLException e) 
						{
							
							e.printStackTrace();
						}
						
						return list;
					}

}
