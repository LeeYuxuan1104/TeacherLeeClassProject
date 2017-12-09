package cn.view;

import java.util.Map;

import com.example.plibraryapp01.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VStateDetailActivity extends Activity implements OnClickListener{
	private Button	 vBack;
	private TextView vTopic,vDetail;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_vuser_detail);
		initView();
		initEvent();
	}
	
	private void initView(){
		vBack=(Button) findViewById(R.id.btnBack);
		vTopic=(TextView) findViewById(R.id.tvTopic);
	}
	private void initEvent(){
		vBack.setText(R.string.back);
		vBack.setOnClickListener(this);
		vTopic.setText(R.string.detail);
		vDetail=(TextView) findViewById(R.id.detail);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		@SuppressWarnings("unchecked")
		Map<String, String> map=(Map<String, String>) bundle.getSerializable("item");
		
		String iid		= 	map.get("iid");
		String count	=	map.get("count");
		String ccount	=	map.get("ccount");
		String ebids	=	map.get("ebids");
		
		
		String item		 = "书目编号:"+iid+"\r\n" +
						   "书目总数:"+count+"\r\n"+
						   "当前数目:"+ccount+"\r\n" +
						   "异常件数:"+ebids+"\r\n" 
						   ;
		vDetail.setText(item);
	}

	@Override
	public void onClick(View view) {
		int nVid=view.getId();
		switch (nVid) {
		case R.id.btnBack:
			finish();
			break;
		default:
			break;
		}
		
	}
}
