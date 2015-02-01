package vn.co.taxinet.mobile.fragment;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.bo.DriderProfileBO;
import vn.co.taxinet.mobile.utils.Const;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProfileFragment extends Fragment {

	public ProfileFragment() {
	}

	private EditText mEmail, mPhone, mPassword, mFirstName, mLastName;
	private MenuItem mSaveMenu, mEditMenu, mCancelMenu;
	private String email, phone, password, firstName, lastName;
	private DriderProfileBO bo;
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		rootView = inflater.inflate(R.layout.activity_driver_profile,
				container, false);
		initialize();
		disableEdittext();
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_profile, menu);
		mSaveMenu = menu.findItem(R.id.save);
		mEditMenu = menu.findItem(R.id.edit);
		mCancelMenu = menu.findItem(R.id.cancel);
		disableEdit();
		super.onCreateOptionsMenu(menu, inflater);
	}

	public void initialize() {
		mEmail = (EditText) rootView.findViewById(R.id.et_email);
		mPhone = (EditText) rootView.findViewById(R.id.et_phone);
		mPassword = (EditText) rootView.findViewById(R.id.et_password);
		mFirstName = (EditText) rootView.findViewById(R.id.et_first_name);
		mLastName = (EditText) rootView.findViewById(R.id.et_last_name);
		bo = new DriderProfileBO();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.edit) {
			enableEdit();
			enableEdittext();
			return true;
		}
		if (id == R.id.save) {
			disableEdittext();
			disableEdit();
			getInfo();
			updateProfile();
			return true;
		}

		if (id == R.id.cancel) {
			disableEdit();
			disableEdittext();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void updateProfile() {
		String result = bo.checkProfile(firstName, lastName, email, phone, password);
		if(result.equalsIgnoreCase(Const.SUCCESS)){
			//send data so server
			
			//save data to database offline
		}
	}

	public void disableEdittext() {
		mEmail.setFocusable(false);
		mPhone.setFocusable(false);
		mPassword.setFocusable(false);
		mFirstName.setFocusable(false);
		mLastName.setFocusable(false);
	}

	public void enableEdittext() {
		mEmail.setFocusable(true);
		mEmail.setFocusableInTouchMode(true);
		mPhone.setFocusable(true);
		mPhone.setFocusableInTouchMode(true);
		mPassword.setFocusable(true);
		mPassword.setFocusableInTouchMode(true);
		mFirstName.setFocusable(true);
		mFirstName.setFocusableInTouchMode(true);
		mLastName.setFocusable(true);
		mLastName.setFocusableInTouchMode(true);
	}

	public void disableEdit() {
		mEditMenu.setVisible(true);
		mSaveMenu.setVisible(false);
		mCancelMenu.setVisible(false);
	}

	public void enableEdit() {
		mEditMenu.setVisible(false);
		mSaveMenu.setVisible(true);
		mCancelMenu.setVisible(true);
	}

	public void getInfo() {
		email = mEmail.getText().toString();
		phone = mPhone.getText().toString();
		password = mPassword.getText().toString();
		firstName = mFirstName.getText().toString();
		lastName = mLastName.getText().toString();
	}
}
