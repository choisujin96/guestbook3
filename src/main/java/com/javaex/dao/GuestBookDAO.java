package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVO;

public class GuestBookDAO {

	
	//필드
			private Connection conn = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;

			private String driver = "com.mysql.cj.jdbc.Driver";
			private String url = "jdbc:mysql://localhost:3306/guestbook_db";
			private String id = "guestbook";
			private String pw = "guestbook";
			
			
			//생성자
			public GuestBookDAO() {
				
			}
			
			
			
			//메소드gs
			
			//메소드일반
			
			// DB연결 메소드 - 공통
			private void connect() {// 메인에서는 사용하지 못함

				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);

					// 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);

				} catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}

			}
				

			// 자원 정리 메소드 -공통
			private void close() {
				// 5. 자원정리
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				  }
			}
		
	
			public List<GuestVO> guestSelect(){
				
				List<GuestVO> guestList = new ArrayList<GuestVO>();
				
				this.connect();
				System.out.println("guestSelect");
				
				
				
				
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
