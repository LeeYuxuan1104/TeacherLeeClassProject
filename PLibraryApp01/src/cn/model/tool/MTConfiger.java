package cn.model.tool;

import com.example.plibraryapp01.R;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.EditText;

public class MTConfiger {
	public static String IP="172.23.43.90";
	public static String PORT="8888";
	public static String PROGRAM="PLibraryManager.v01";
	public static String FAIL="fail";
	public static int USER_RESIGN=1;
	public static int USER_LOGIN=2;
	public static int USER_QUERY_PAGE_CONDITION=3;
	public static int USER_DEL_ALL=4;
	public static int USER_DEL_ITEM=5;
	
	public MTConfiger() {
	
	}
	
	public String docheckEditView(EditText view){
		String text=view.getText().toString().trim();
		if(text.equals("")||"".equals(text)){
			return "null";
		}
		return text;
	}
	public void doclearEditView(EditText view){
		view.setText("");
	}
	public void exitSystem(final Activity context){
		Builder builder=new Builder(context);
		builder.setTitle(R.string.exit);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				context.finish();
			}
		});
		builder.setNegativeButton(R.string.no, null);
		builder.create();
		builder.show();
	}
}
