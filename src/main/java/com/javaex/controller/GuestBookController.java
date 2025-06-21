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
    //
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("GusetBookController");
		
		String action = request.getParameter("action");
		System.out.println(action); //업무구분
		
		
		//등록+리스트
		if("list".equals(action)) {
			
			System.out.println("리스트33"); //확인
			
			GuestBookDAO guestbookDAO = new GuestBookDAO();
			
			List<GuestVO> guestList = guestbookDAO.guestSelect();
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		} else if()
		
		
		
		
		
		
	}


	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
