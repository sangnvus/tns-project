package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.alert.AlertDialogManager;
import vn.co.taxinet.mobile.bo.RegisterBO;
import vn.co.taxinet.mobile.utils.ConnectionDetector;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private CheckBox mCbTerm;
	private EditText mEmail, mPassword, mConfirmPassword, mFirstName,
			mLastName, mPhoneNumber;
	public static String email, password, confirmPassword, firstName, lastName,
			phoneNumber;
	// Connection detector
	private ConnectionDetector cd;
	private AlertDialogManager alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mCbTerm = (CheckBox) findViewById(R.id.cb_term);
		initialize();
	}

	public void term(View v) {
		Intent it = new Intent(RegisterActivity.this, TermActivity.class);
		startActivityForResult(it, 1);
	}

	public void register(View v) {
		if (mCbTerm.isChecked()) {
			// check internet connection before register
			if (cd.isConnectingToInternet()) {
				getInfo();
				String result = RegisterBO.checkRegisterInfo(email, password,
						confirmPassword, firstName, lastName, phoneNumber);
				if (result.equals(Const.SUCCESS)) {
					RegisterBO.register(RegisterActivity.this, email, password, firstName,
							lastName, phoneNumber);
				} else {
					// show error alert
					alert.showAlertDialog(RegisterActivity.this,
							getResources().getString(
									R.string.alert_invalid_info), result, false);
				}
			} else {
				// show error message
				alert.showAlertDialog(
						RegisterActivity.this,
						getResources().getString(
								R.string.alert_internet_error_title),
						getResources().getString(
								R.string.alert_internet_error_message), false);
			}
		} else {
			// show error message
			alert.showAlertDialog(
					RegisterActivity.this,
					getResources().getString(
							R.string.alert_term_title),
					getResources().getString(
							R.string.alert_term_message), false);
		}

	}

	public void getInfo() {
		email = mEmail.getText().toString();
		password = mPassword.getText().toString();
		confirmPassword = mConfirmPassword.getText().toString();
		firstName = mFirstName.getText().toString();
		lastName = mLastName.getText().toString();
		phoneNumber = mPhoneNumber.getText().toString();
	}

	public void initialize() {
		mEmail = (EditText) findViewById(R.id.et_email);
		mPassword = (EditText) findViewById(R.id.et_password);
		mConfirmPassword = (EditText) findViewById(R.id.et_cfpassword);
		mFirstName = (EditText) findViewById(R.id.et_first_name);
		mLastName = (EditText) findViewById(R.id.et_last_name);
		mPhoneNumber = (EditText) findViewById(R.id.et_phone_number);

		cd = new ConnectionDetector(this);

		alert = new AlertDialogManager();
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
