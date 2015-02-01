package vn.co.taxinet.mobile.bo;

import vn.co.taxinet.mobile.utils.Const;
import vn.co.taxinet.mobile.utils.Validator;

/**
 * @author Hieu-Gie
 * 
 * @createDate 20/1/2014
 */

public class DriderProfileBO {

	private Validator emailValidator = new Validator();
	
	/**
	 * @author Hieu-Gie
	 * 
	 *         kiểm tra dữ liệu ,gửi dữ liệu lên server và lưu dữ liệu offline
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param password
	 */

	public String checkProfile(String firstName, String lastName, String email,
			String phone, String password) {

		//check if no info change
		
		
		// check null
		if (firstName.equalsIgnoreCase("")) {
			return Const.FIRST_NAME_ERROR;
		}
		if (lastName.equalsIgnoreCase("")) {
			return Const.LAST_NAME_ERROR;
		}
		if (email.equalsIgnoreCase("")) {
			return Const.EMAIL_ERROR;
		}
		if (phone.equalsIgnoreCase("")) {
			return Const.PHONE_NUMBER_ERROR;
		}
		if (password.equalsIgnoreCase("")) {
			return Const.PASSWORD_ERROR;
		}
		
		//check length
		if (password.length() < 6) {
			return Const.PASSWORD_ERROR;
		}
		
		//check email
		
		if(!emailValidator.validateEmail(email)){
			return Const.EMAIL_ERROR;
		}

		return Const.SUCCESS;

	}
}
