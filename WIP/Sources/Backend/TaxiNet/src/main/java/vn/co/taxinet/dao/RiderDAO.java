package vn.co.taxinet.dao;

import java.util.Date;

import vn.co.taxinet.orm.Rider;

public interface RiderDAO extends BaseDAO{
	Rider select(String uid);
	
	void updatePassword ( String uid, String rePassword);
	
	void updateProfile(String uid, String surName, String name, String countryCode,
			String phoneNo, String languageCode, String zipCode, Date date, String username);
}
