package cn.view;

import com.example.plibraryapp01.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VUserDetailActivity extends Activity implements OnClickListener{
	private Context	 mContext;
	private Button	 vBack,vFunction;
	private TextView vTopic;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_vuser_detail);
		initView();
		initEvent();
	}
	
	private void initView(){
		vBack=(Button) findViewById(R.id.btnBack);
		vFunction=(Button) findViewById(R.id.btnFunction);
	}
	private void initEvent(){
		mContext=VUserDetailActivity.this;
		vBack.setText(R.string.back);
		vFunction.setText(R.string.delall);
		vTopic.setText(R.string.detail);
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
