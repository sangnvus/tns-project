package vn.co.taxinet.mobile.newactivity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.bo.ForgotPasswordBO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity {

	private EditText mInfo;
	private String info;
	private ForgotPasswordBO mForgotPasswordBO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		mInfo = (EditText) findViewById(R.id.et_info);
		mForgotPasswordBO = new ForgotPasswordBO();
	}

	public void getPassword(View v) {
		info = mInfo.getText().toString();
		boolean checkInfo = mForgotPasswordBO.checkInfo(info);
		if (checkInfo) {
			Intent it = new Intent(ForgotPasswordActivity.this,
					ResetPasswordActivity.class);
			startActivity(it);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
