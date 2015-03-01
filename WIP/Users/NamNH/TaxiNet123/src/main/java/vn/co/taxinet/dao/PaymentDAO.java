package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Payment;

public interface PaymentDAO extends BaseDAO {
	public Payment findById(java.lang.Integer id);
}
