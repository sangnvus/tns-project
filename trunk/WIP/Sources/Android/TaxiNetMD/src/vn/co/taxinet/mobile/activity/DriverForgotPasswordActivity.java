package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.bo.DriverForgotPasswordBO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DriverForgotPasswordActivity extends Activity {

	private EditText mInfo;
	private String info;
	private DriverForgotPasswordBO mDriverForgotPasswordBO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_forgot_password);
		mInfo = (EditText) findViewById(R.id.et_info);
		mDriverForgotPasswordBO = new DriverForgotPasswordBO();
	}

	public void getPassword(View v) {
		info = mInfo.getText().toString();
		boolean checkInfo = mDriverForgotPasswordBO.checkInfo(info);
		if (checkInfo) {
			Intent it = new Intent(DriverForgotPasswordActivity.this,
					DriverResetPasswordActivity.class);
			startActivity(it);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
