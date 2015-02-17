package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Language;

public interface LanguageDAO extends BaseDAO {
	public Language findById(java.lang.String id);
}
