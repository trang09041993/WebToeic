package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.slideBanner;

public class HomeDAO {
	
	public static List<slideBanner> displaySileBanner(Connection con){
		
		List<slideBanner> listBanner=new ArrayList<slideBanner>();
		String sql="select * from slideBanner";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				slideBanner slide=new slideBanner();
				String name=rs.getString(2);
				String content=rs.getString(3);
				String image=rs.getString(4);
				
				slide.setSlideName(name);
				slide.setSlideContent(content);
				slide.setSlideImage(image);
				
				listBanner.add(slide);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listBanner;
	}

}






















