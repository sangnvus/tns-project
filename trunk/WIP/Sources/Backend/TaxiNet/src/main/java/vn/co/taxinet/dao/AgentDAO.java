package vn.co.taxinet.dao;

import vn.co.taxinet.orm.Agent;

public interface AgentDAO extends BaseDAO{
	public Agent findById(java.lang.String id);
}
