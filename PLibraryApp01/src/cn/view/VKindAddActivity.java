package cn.view;

import com.example.plibraryapp01.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VKindAddActivity extends Activity implements OnClickListener{
	private Context  mContext;
	private Button   vBack;
	private TextView vTopic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_vkind_add);
		initView();
		initEvent();
	}
	private void initView(){
		vBack=(Button) findViewById(R.id.btnBack);
		vTopic=(TextView) findViewById(R.id.tvTopic);
	}
	private void initEvent(){
		mContext=VKindAddActivity.this;
		vBack.setText(R.string.back);
		vTopic.setText(R.string.kindinfo);
		vBack.setOnClickListener(this);
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
