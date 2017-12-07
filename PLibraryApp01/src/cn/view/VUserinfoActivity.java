package cn.view;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.model.tool.MTConfiger;
import cn.model.tool.MTGetOrPostHelper;
import com.example.plibraryapp01.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VUserinfoActivity extends Activity implements OnClickListener{
	private Context  mContext;
	private Button   vBack,vSearch,vUpPage,vDownPage,vFunction;
	private Builder	 vBuilder;
	private TextView vTopic;
	private Spinner  vuserkind;
	private EditText vValue,vPage;
	private ListView vlistView;
	//
	private ArrayAdapter<String> adapter;
	
	//	进行线程控件;
	private ProgressDialog 	vDialog; // 对话方框;
	//	自定义类;
	private MyThread   myThread; 
	private MTConfiger mtConfiger;
	private int 	   nUpOrDown=1; 
	private ArrayList<Map<String, String>> listdata;
	private SimpleAdapter	mAdapter;
	//	参数
	private String[] names={"用户编号","用户名称","用户权限"};
	private String[] kinds={"uid","uname","urole"};
	//
	private int nCurrentPage=1;
	private int nCountLimit=2;
	private String value=" ";
	private String pkind="";
	//	控制线程;
	@SuppressLint("HandlerLeak") 
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int nFlag = msg.what;
			vDialog.dismiss();
			switch (nFlag) {
			// 01.成功;
			case 1:
				Toast.makeText(mContext, R.string.update,Toast.LENGTH_SHORT).show();
				break;
			// 02.失败;
			case 2:
				Toast.makeText(mContext, R.string.nodata, Toast.LENGTH_LONG).show();
				switch ( nUpOrDown) {
				case 1:
					nCurrentPage++;
					break;
				case 2:					
					nCurrentPage--;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
			loadData();
			vPage.setText("第"+nCurrentPage+"页");
			if(myThread!=null){
				myThread.interrupt();
				myThread=null;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_vuser);
		initView();
		initEvent();
	}
	
	private void initView(){
		vBack=(Button) findViewById(R.id.btnBack);
		vTopic=(TextView) findViewById(R.id.tvTopic);
		vuserkind=(Spinner) findViewById(R.id.spKind);
		vSearch=(Button) findViewById(R.id.btnSearch);
		vValue=(EditText) findViewById(R.id.etValue);
		vPage=(EditText) findViewById(R.id.etPage);
		vUpPage=(Button) findViewById(R.id.btnUpPage);
		vDownPage=(Button) findViewById(R.id.btnDownPage);
		vlistView=(ListView) findViewById(R.id.listView);
		vFunction=(Button) findViewById(R.id.btnFunction);
	}
	private void initEvent(){
		mContext	=	VUserinfoActivity.this;
		mtConfiger	=	new MTConfiger();
		
		
		vBack.setText(R.string.back);
		vTopic.setText(R.string.userinfo);
		vBack.setOnClickListener(this);
		vSearch.setOnClickListener(this);
		vUpPage.setOnClickListener(this);
		vDownPage.setOnClickListener(this);
		vFunction.setVisibility(View.VISIBLE);
		vFunction.setText(R.string.delall);
		vFunction.setOnClickListener(this);
		
		adapter=new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line, names);
		vuserkind.setAdapter(adapter);
		
		vuserkind.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				
				pkind=kinds[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				pkind=" ";
				
			}
		});
		
		if(myThread==null){
			final CharSequence strDialogTitle = getString(R.string.wait);
			final CharSequence strDialogBody = getString(R.string.doing);
			vDialog = ProgressDialog.show(mContext, strDialogTitle,strDialogBody, true);
			myThread=new MyThread(nCurrentPage, nCountLimit, "","null","null",0);
			myThread.start();
		}
		
		//	输入框变化的内容;
		vValue.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				nCurrentPage=1;
				listdata.clear();
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//	列表显示的内容;
		vlistView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				vBuilder=new Builder(mContext);
				vBuilder.setTitle(R.string.delitem);
				final int id=Integer.parseInt(listdata.get(position).get("id"));
				vBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						nCurrentPage=1;
						nUpOrDown=0;
						final CharSequence strDialogTitle = getString(R.string.wait);
						final CharSequence strDialogBody = getString(R.string.doing);
						vDialog = ProgressDialog.show(mContext, strDialogTitle,strDialogBody, true);
						myThread=new MyThread(nCurrentPage, nCountLimit, pkind, "null","delitem",id);
						myThread.start();
					}
				});
				vBuilder.setNegativeButton(R.string.no, null);
				vBuilder.create();
				vBuilder.show();
				return false;
			}
		});
		
	}

	private void loadData(){
		mAdapter=new SimpleAdapter(mContext, listdata, R.layout.act_item, new String[]{"number","content","id"}, new int []{R.id.number,R.id.content,R.id.id});
		vlistView.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View view) {
		int nVid=view.getId();
		switch (nVid) {
		//	返回键;
		case R.id.btnBack:
			finish();
			break;
		//	搜索键;
		case R.id.btnSearch:
			value=mtConfiger.docheckEditView(vValue);
			nCurrentPage=1;
			nUpOrDown=0;
			if(myThread==null){
				final CharSequence strDialogTitle = getString(R.string.wait);
				final CharSequence strDialogBody = getString(R.string.doing);
				vDialog = ProgressDialog.show(mContext, strDialogTitle,strDialogBody, true);
				myThread=new MyThread(nCurrentPage, nCountLimit, pkind, value,"null",0);
				myThread.start();
			}

			break;
		//	上一页;
		case R.id.btnUpPage:
			nUpOrDown=1;
			value=mtConfiger.docheckEditView(vValue);
			if(myThread==null){
				nCurrentPage--;
				final CharSequence strDialogTitle = getString(R.string.wait);
				final CharSequence strDialogBody = getString(R.string.doing);
				vDialog = ProgressDialog.show(mContext, strDialogTitle,strDialogBody, true);
				myThread=new MyThread(nCurrentPage, nCountLimit, pkind, value,"null",0);
				myThread.start();
			}	
			
			break;
		//	下一页;
		case R.id.btnDownPage:
			nUpOrDown=2;
			value=mtConfiger.docheckEditView(vValue);
			if(myThread==null){
				nCurrentPage++;
				final CharSequence strDialogTitle = getString(R.string.wait);
				final CharSequence strDialogBody = getString(R.string.doing);
				vDialog = ProgressDialog.show(mContext, strDialogTitle,strDialogBody, true);
				myThread=new MyThread(nCurrentPage, nCountLimit, pkind, value,"null",0);
				myThread.start();
			}
			
			break;
		//	清空操作;
		case R.id.btnFunction:
			vBuilder=new Builder(mContext);
			vBuilder.setTitle(R.string.delall);
			vBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					if(myThread==null){
						nCurrentPage=1;
						nUpOrDown=0;
						final CharSequence strDialogTitle = getString(R.string.wait);
						final CharSequence strDialogBody = getString(R.string.doing);
						vDialog = ProgressDialog.show(mContext, strDialogTitle,strDialogBody, true);
						myThread=new MyThread(nCurrentPage, nCountLimit, pkind, "null","delall",0);
						myThread.start();
					}
				}
			});
			vBuilder.setNegativeButton(R.string.no, null);
			vBuilder.create();
			vBuilder.show();
			nCurrentPage=1;
			break;
		default:
			break;
		}
		
	}
	
	//	查询线程
	// 定义的线程——自定义的线程内容;
		public class MyThread extends Thread {
			private MTGetOrPostHelper mGetOrPostHelper;
			private int 	currentpage,countlimit;
			private String  pkind,value,order;
			private int 	id;
			public MyThread(int currentpage,int countlimit,String pkind,String value,String order,int id) {
				this.mGetOrPostHelper=new MTGetOrPostHelper();
				this.currentpage=currentpage;
				this.countlimit=countlimit;
				this.pkind=pkind;
				this.value=value;
				this.order=order;
				this.id=id;
			}
			@Override
			public void run() {
				int nFlag = 1;
				// 进行相应的登录操作的界面显示;
				// 01.Http 协议中的Get和Post方法;
				String url 	  	;
				String param;
				String response	 = "fail";
				try {
					url	  = "http://"+MTConfiger.IP+":"+MTConfiger.PORT+"/"+MTConfiger.PROGRAM+"/user_info";
					if(order.equals("delall")){
						param = 
						"opertype="+MTConfiger.USER_DEL_ALL;
						response  = mGetOrPostHelper.sendGet(url, param);
						listdata.clear();
					}
					
					if(order.equals("delitem")){
						param = 
						"opertype="+MTConfiger.USER_DEL_ITEM+"" +
						"&id="+id;
						response  = mGetOrPostHelper.sendGet(url, param);
						listdata.clear();
					}
					param = 
					"opertype="+MTConfiger.USER_QUERY_PAGE_CONDITION+"&" +
					"currentpage="+currentpage+"&" +
					"countlimit="+countlimit+"&" +
					"pkind="+pkind+"&" +
					"value="+URLEncoder.encode(value,"utf-8");
					response  = mGetOrPostHelper.sendGet(url, param);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				if (response.trim().equals("fail")) {
					nFlag = 2;
				}else{
					try {
						listdata		= new ArrayList<Map<String,String>>();
						JSONArray array = new JSONArray(response);
						int i = 0;
						JSONObject obj = null;
						do {
							try {
								// JsonObject的解析;
								obj 			 = array.getJSONObject(i);
								int    number	 = i+1;
								String id 		 = obj.getString("id");
								String uid 		 = obj.getString("uid");
								String uname 	 = obj.getString("uname");
								String upwd 	 = obj.getString("upwd");
								
								String urole 	 = obj.getString("urole");
								String note	 	 = obj.getString("note");
								String img  	 = obj.getString("img");
								String phone 	 = obj.getString("phone");
								String email 	 = obj.getString("email");
				
								Map<String, String> map=new HashMap<String, String>();
								map.put("number", number+"");
								map.put("id", id);
								map.put("uid", uid);
								map.put("uname", uname);
								map.put("upwd", upwd);
								map.put("urole", urole);
								map.put("note", note);
								map.put("img", img);
								map.put("phone", phone);
								map.put("email", email);
								map.put("content","编号:"+uid+" | 姓名:"+uname+" | 权限:"+urole);
								listdata.add(map);
								i++;
							} catch (Exception e) {
								obj = null;
							}
						} while (obj != null);
					} catch (JSONException e) {
						nFlag = 2;
					}
				}
				mHandler.sendEmptyMessage(nFlag);
			}
		}
}
