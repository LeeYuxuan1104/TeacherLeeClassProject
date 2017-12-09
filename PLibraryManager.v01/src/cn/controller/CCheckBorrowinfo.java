package cn.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.model.entity.MBorrowinfo;


public class CCheckBorrowinfo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		/////
		PrintWriter 	pWriter = 	resp.getWriter();
		MBorrowinfo		borrowinfo= null;
		int 			operType=	Integer.parseInt(req.getParameter("opertype"));
		String  		sResult =   "fail";
		String 			id,bid,iid,iname,borrower,btime,deadline,state,outstate,instate,inimg=null;
		/////
		switch (operType) {
		///	注册;
		case 1:

			break;
		///	登录;
		case 2:

			break;
		///	显示前10条信息;
		case 3:
			int nCurrentPage=	Integer.parseInt(req.getParameter("currentpage"));
			int nCountLimit =	Integer.parseInt(req.getParameter("countlimit"));
			String pkind	=	new String(req.getParameter("pkind").getBytes("ISO8859_1"),"utf-8");
			String value	=	new String(req.getParameter("value").getBytes("ISO8859_1"),"utf-8");
			borrowinfo		=	new MBorrowinfo();
			sResult 		=	borrowinfo.queryBorrowinfoByPageAndCondition(nCurrentPage, nCountLimit, pkind, value);
			break;
			
		///	清空操作;
		case 4:
			borrowinfo= new MBorrowinfo();
			sResult   =	borrowinfo.delAll();
			break;
			
		///	单条删除;
		case 5:
			id		=	new String(req.getParameter("id").getBytes("ISO8859_1"),"utf-8");
			borrowinfo= new MBorrowinfo(); 
			sResult =		borrowinfo.delItem(id);
			break;
			
		///	单条新增;
		case 6:
			bid		= 	new String(req.getParameter("bid").getBytes("ISO8859_1"),"utf-8");
			iid		=	new String(req.getParameter("iid").getBytes("ISO8859_1"),"utf-8");
			iname	=	new String(req.getParameter("iname").getBytes("ISO8859_1"),"utf-8");
			borrower=	new String(req.getParameter("borrower").getBytes("ISO8859_1"),"utf-8");
			btime	=	new String(req.getParameter("btime").getBytes("ISO8859_1"),"utf-8");
			deadline=	new String(req.getParameter("deadline").getBytes("ISO8859_1"),"utf-8");
			state	=	new String(req.getParameter("state").getBytes("ISO8859_1"),"utf-8");
			outstate=	new String(req.getParameter("outstate").getBytes("ISO8859_1"),"utf-8");
			instate	=	new String(req.getParameter("instate").getBytes("ISO8859_1"),"utf-8");
			inimg	=	new String(req.getParameter("inimg").getBytes("ISO8859_1"),"utf-8");
			borrowinfo=new MBorrowinfo(bid, iid, iname, borrower, btime, deadline, state, outstate, instate, inimg);
			sResult =	borrowinfo.insertBorrowinfo();
			break;
		///	单条查询;
		case 7:
			id		=	new String(req.getParameter("id").getBytes("ISO8859_1"),"utf-8");
			borrowinfo= new MBorrowinfo(); 
			sResult =	borrowinfo.queryBorrowinfoItem(id);
			break;
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
