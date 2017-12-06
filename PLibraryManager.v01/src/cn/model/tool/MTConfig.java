package cn.model.tool;

import java.util.ArrayList;

public class MTConfig {
	//	数据库交互的工具类;
	private MTDataBaseTool mtDataBaseTool;
	public MTConfig() {
		mtDataBaseTool=new MTDataBaseTool();
		//初始化表单
		checkTableStruction();
	}
	
	/*进行数据表单的检测*/
	public void checkTableStruction(){
		String sql="select * from user_info";
		ArrayList<String[]> tables= mtDataBaseTool.query(sql);
		if(tables==null){
			ArrayList<String> list=new ArrayList<>();
			list.add(table_user_info);
			list.add(table_kind_book_info);
			list.add(table_item_book_info);
			list.add(table_borrow_info);
			list.add(table_book_state_info);
			for(String item:list){
				mtDataBaseTool.doDBUpdate(item);
			}
		}
	}
	
	/*初始化表单结构*/
	// 用户信息表;
	private String table_user_info=
			"create table user_info (" +
			"id Integer primary key auto_increment," +
			"uid varchar(32) not null," +
			"uname varchar(32) not null," +
			"upwd varchar(32) not null," +
			"urole varchar(32) not null," +
			"note varchar(500)," +
			"img varchar(500) ," +
			"phone varchar(32) not null," +
			"email varchar(32) not null" +
			")";
	//	书大类信息;
	private String table_kind_book_info=
			"create table kind_book_info (" +
			"id integer primary key auto_increment," +
			"kid varchar(32) not null," +
			"kname varchar(32) not null," +
			"note varchar(500))";
	//	书小类信息;
	private String table_item_book_info=
			"create table item_book_info (" +
			"id integer primary key auto_increment," +
			"iid varchar(32) not null," +
			"iname varchar(32) not null," +
			"note varchar(500)," +
			"author varchar(32) not null," +
			"press varchar(100) not null," +
			"ptime varchar(100) not null," +
			"count varchar(100) not null," +
			"kid varchar(32) not null," +
			"img varchar(100)" +
			")";
	//	书借阅信息;
	private String table_borrow_info=
			"create table borrow_info (" +
			"id integer primary key auto_increment," +
			"bid varchar(32) not null," +
			"iid varchar(32) not null," +
			"iname varchar(32) not null," +
			"borrower varchar(32) not null," +
			"btime varchar(32) not null," +
			"deadline varchar(32) not null," +
			"state varchar(32) not null," +
			"outstate varchar(32) not null," +
			"instate varchar(32) not null," +
			"inimg varchar(1000) " +
			")";
	//	借书状态信息;
	private String table_book_state_info=
			"create table book_state_info (" +
			"id integer primary key auto_increment," +
			"iid varchar(32) not null," +
			"count varchar(32) not null," +
			"ccount varchar(32) not null," +
			"ebids varchar(1000))";
	///////////////////////////////////////////////////
	
	
}
