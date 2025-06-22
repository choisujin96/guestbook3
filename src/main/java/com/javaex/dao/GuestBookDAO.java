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
			private void connect() {

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
		
	
			//등록+리스트 
			public List<GuestVO> guestSelect() {
				
				List<GuestVO> guestList = new ArrayList<GuestVO>();
				
				this.connect();
				System.out.println("guestSelect");
				
				
				//3. SQL문 준비/ 바이딩/ 실행
				try {
					//SQL문 준비
					String query =" ";
					query +=" select 	no ";
					query +="		   ,name ";
					query +="		   ,password ";
					query +="          ,content ";
					query +="          ,reg_date ";
					query +=" from guestbook ";
			
					//바이딩
					pstmt = conn.prepareStatement(query);
					
					//실행
					rs = pstmt.executeQuery();
					
					//4. 결과처리
					while(rs.next()) {
						
						
						int no = rs.getInt("no");
						String name = rs.getString("name");
						String pw = rs.getString("password");
						String content = rs.getString("content");
						String regdate = rs.getString("reg_date");
						
				
						GuestVO guestVO = new GuestVO(no, name, pw, content, regdate);
						
						guestList.add(guestVO);
					}
				
				}catch (SQLException e) {
					System.out.println("error:" + e);
				}
				
				this.close();
				return guestList;
				
				//등록업무
			} 	public int guestInsert(GuestVO guestVO) {
				System.out.println("guestInsert");
				
				int count = -1;
				this.connect();
				
				//3. SQL문 준비/ 바인딩/ 실행
				try {
					//SQL문 준비
					String query = " insert into guestbook ";
					query += " values(null, ?, ?, ?, now()); ";

					//-바인딩
					pstmt = conn.prepareStatement(query);
					
					pstmt.setString(1, guestVO.getName());
					pstmt.setString(2, guestVO.getPassword());
					pstmt.setString(3, guestVO.getContent());
				
					
					//-실행
					count = pstmt.executeUpdate();
					
					//4. 결과처리
					System.out.println(count + " 건이 저장되었습니다.");

				} catch (SQLException e) {
					System.out.println("error:" + e);			}
				
				this.close();
				return count;
		
				//삭제
			} 	public int guestDelete (int no, String pw) {
				
				System.out.println("guestDelete");
				
				int count = -1;
				
				this.connect();
				
				//3. SQL문 준비/ 바인딩/ 실행
				try {
					//-SQL문 준비
					String query =" delete from  guestbook ";
					query += " where no = ? ";
					query += " and password = ? ";
					
					//-바인딩
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, no);
					pstmt.setString(2, pw);

					
					//-실행
					count = pstmt.executeUpdate();
					
					//4. 결과처리
					System.out.println(count + " 건이 삭제되었습니다.");
					
				}catch (SQLException e) {
					System.out.println("error:" + e);
				
				}
				
				this.close();
				
				return count;

			}


}
