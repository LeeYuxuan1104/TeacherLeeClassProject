package cn.view;

import cn.model.tool.MTConfiger;

import com.example.plibraryapp01.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VMenuActivity extends Activity implements OnClickListener{
	private Context		mContext;
	private Intent 		mIntent;
	private Button 		vBack,vUserinfo,vKindinfo,vIteminfo,vBorrowinfo,vStateinfo;
    
	private TextView 	vTopic;
	
	private MTConfiger	mtConfiger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_vmenu);
		initView();
		initEvent();
	}
	
	private void initView(){
		vTopic=(TextView) findViewById(R.id.tvTopic);
		vBack=(Button) findViewById(R.id.btnBack);
		
		vUserinfo=(Button) findViewById(R.id.btnUserinfo);
		vKindinfo=(Button) findViewById(R.id.btnKindinfo);
		vIteminfo=(Button) findViewById(R.id.btnIteminfo);
		vBorrowinfo=(Button) findViewById(R.id.btnBorrowinfo);
		vStateinfo=(Button) findViewById(R.id.btnStateinfo);
		
	}
	
	private void initEvent(){
		mContext	=	VMenuActivity.this;
		mtConfiger	=	new MTConfiger();
		vTopic.setText(R.string.menuinfo);
		vBack.setText(R.string.exit);
		vBack.setOnClickListener(this);
		
		vUserinfo.setOnClickListener(this);
		vKindinfo.setOnClickListener(this);
		vIteminfo.setOnClickListener(this);
		vBorrowinfo.setOnClickListener(this);
		vStateinfo.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		int nVid=view.getId();
		switch (nVid) {
		case R.id.btnBack:
			mtConfiger.exitSystem(VMenuActivity.this);
			break;
		case R.id.btnUserinfo:
			mIntent=new Intent(mContext, VUserinfoActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnKindinfo:
			mIntent=new Intent(mContext, VKindinfoActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnIteminfo:
			mIntent=new Intent(mContext, VIteminfoActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnBorrowinfo:
			mIntent=new Intent(mContext, VBorrowinfoActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btnStateinfo:
			mIntent=new Intent(mContext, VStateinfoActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}
		
	}

}
