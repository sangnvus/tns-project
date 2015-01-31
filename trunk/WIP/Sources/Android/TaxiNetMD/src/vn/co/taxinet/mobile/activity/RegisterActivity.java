package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class RegisterActivity extends Activity {

	private CheckBox mCbTerm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_register);
		mCbTerm = (CheckBox) findViewById(R.id.cb_term);
	}

	public void term(View v) {
		Intent it = new Intent(RegisterActivity.this,
				TermActivity.class);
		startActivityForResult(it, 1);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				mCbTerm.setChecked(true);
			}
			if (resultCode == RESULT_CANCELED) {
				mCbTerm.setChecked(false);
			}
		}
	}
}
