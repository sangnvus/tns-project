/**
 * 
 */
package vn.co.taxinet.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.orm.Rider;
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
		String hql = " FROM TaxiNetUsers U WHERE U.username = :userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", uid.toLowerCase());
		// TODO Auto-generated method stub
		List<TaxiNetUsers> result = query.list();
		TaxiNetUsers user = null;
		if(!result.isEmpty()) {
			user = result.get(0) ;
		}
		return user;
	}

	public boolean registerTaxiNetUser(TaxiNetUsers user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean registerRider(Rider rider) {
		// TODO Auto-generated method stub
		return false;
	}	

}
