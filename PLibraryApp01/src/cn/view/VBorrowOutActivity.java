package cn.view;

import com.example.plibraryapp01.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VBorrowOutActivity extends Activity implements OnClickListener{
	private Button vBack;
	private TextView vTopic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_vborrow_out);
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
		vTopic.setText(R.string.borrow);
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
