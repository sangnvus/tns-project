/**
 * 
 */
package vn.co.taxinet.dao.impl;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.TaxiNetUsers;

/**
 * @author DEV
 *
 */
@Transactional
public class TaxiNetUserDAOImpl extends BaseDAOImpl implements TaxiNetUserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transactional(readOnly=true)
	public TaxiNetUsers select(String uid) {
		Session session = getSessionFactory().getCurrentSession();
		// TODO Auto-generated method stub
		TaxiNetUsers user = (TaxiNetUsers) session.get(TaxiNetUsers.class, uid);
		return user;
	}	

}
