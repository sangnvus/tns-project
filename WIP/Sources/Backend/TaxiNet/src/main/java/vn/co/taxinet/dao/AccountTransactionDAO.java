package vn.co.taxinet.dao;

import java.util.Date;
import java.util.List;

import vn.co.taxinet.orm.AccountTransaction;

public interface AccountTransactionDAO extends BaseDAO{
	public List<AccountTransaction> pagination(String username, int page, int numberOfElement, Date fromDate, Date toDate);
}
