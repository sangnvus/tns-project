package vn.co.taxinet.bo;

import vn.co.taxinet.orm.TaxiNetUsers;

public interface TaxiNetUserBO {
	public String register(String username, String password, String email, String userGroup,
		String languageCode, String countryCode);
}
