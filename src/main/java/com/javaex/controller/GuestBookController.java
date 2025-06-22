package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDAO;
import com.javaex.vo.GuestVO;


@WebServlet("/gBook3")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GuestBookController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("GusetBookController");
		
		String action = request.getParameter("action");
		System.out.println(action); 
		
		
		//등록+리스트
		if("list".equals(action)) {
			
			System.out.println("리스트33"); //확인 ㅇㅋ
			
			GuestBookDAO guestbookDAO = new GuestBookDAO();
			
			List<GuestVO> guestList = guestbookDAO.guestSelect();
			
			request.setAttribute("gList", guestList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		  //등록업무
		} else if ("write".equals(action)) {
			
			System.out.println("등록"); //확인 ㅇㅋ
			
		
			String name = request.getParameter("name");
			String pw = request.getParameter("password");
			String content = request.getParameter("content");
		
			
			GuestVO guestVO = new GuestVO(name, pw, content);
					
		
			GuestBookDAO guestbookDAO = new GuestBookDAO();
			guestbookDAO.guestInsert(guestVO);

			
			response.sendRedirect("http://localhost:8088/guestbook3/gBook3?action=list");
			
		   //삭제폼	
		}  else if("dform".equals(action)) { 
			
			System.out.println("삭제폼"); //확인ㅇㅋ
			
			 // 포워드
		    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF//deleteForm.jsp");
		    rd.forward(request, response);
		
		} else if("delete".equals(action)) { //삭제
			System.out.println("삭제"); //확인오케
			
			int no = Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("password");
			
			GuestVO guestVO = new GuestVO(no, pw);
			
			
			GuestBookDAO guestbookDAO = new GuestBookDAO();
			guestbookDAO.guestDelete(no, pw);
			
			response.sendRedirect("http://localhost:8088/guestbook3/gBook3?action=list");
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
