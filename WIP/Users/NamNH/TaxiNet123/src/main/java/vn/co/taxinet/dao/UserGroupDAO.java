package vn.co.taxinet.dao;

import vn.co.taxinet.orm.UserGroup;

public interface UserGroupDAO extends BaseDAO {
	public UserGroup findById(java.lang.String id);
}
