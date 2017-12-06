package cn.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.model.entity.MUserinfo;

public class CCheckUserinfo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		/////
		PrintWriter 	pWriter = 	resp.getWriter();
		MUserinfo		userinfo=	null;
		int 			operType=	Integer.parseInt(req.getParameter("opertype"));
		String  		sResult =   "fail";
		String 			uid,uname,upwd,urole,note,img=null,phone,email;
		/////
		switch (operType) {
		///	注册;
		case 1:
			uid		= 	new String(req.getParameter("uid").getBytes("ISO8859_1"),"utf-8");
			uname	=	new String(req.getParameter("uname").getBytes("ISO8859_1"),"utf-8");
			upwd	=	new String(req.getParameter("upwd").getBytes("ISO8859_1"),"utf-8");
			
			urole	=	new String(req.getParameter("urole").getBytes("ISO8859_1"),"utf-8");
			note	=	new String(req.getParameter("note").getBytes("ISO8859_1"),"utf-8");
			phone	=	new String(req.getParameter("phone").getBytes("ISO8859_1"),"utf-8");
			email	=	new String(req.getParameter("email").getBytes("ISO8859_1"),"utf-8");
			userinfo=	new MUserinfo(uid, uname, upwd, urole, note, img, phone, email);
			sResult = 	userinfo.insertUserinfo();
			
			break;
		///	登录;
		case 2:
			uid		= 	new String(req.getParameter("uid").getBytes("ISO8859_1"),"utf-8");
			upwd	=	new String(req.getParameter("upwd").getBytes("ISO8859_1"),"utf-8");
			userinfo=	new MUserinfo();
			sResult =   userinfo.checkUserinfo(uid, upwd);
			break;
		///	显示前10条信息;
		case 3:
			
		default:
			break;
		}

		
		pWriter.print(sResult)	;
		pWriter.flush();
		pWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
