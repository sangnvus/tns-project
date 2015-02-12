/**
 * 
 */
package vn.co.taxinet.dao.impl;

import java.util.ArrayList;
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

	@Transactional(readOnly = true)
	public TaxiNetUsers select(String uid) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = " FROM TaxiNetUsers U WHERE U.username = :userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", uid);
		// TODO Auto-generated method stub
		List<TaxiNetUsers> result = query.list();
		TaxiNetUsers user = new TaxiNetUsers();
		if (!result.isEmpty()) {
			user = result.get(0);
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

	/* (non-Javadoc)
	 * @see vn.co.taxinet.dao.TaxiNetUserDAO#listAllUsers()
	 */
	public List<TaxiNetUsers> listAllUsers(String username, String email) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from taxinetusers";
		String hql1 = "	inner join usergroup on taxinetusers.groupcode = usergroup.groupcode";
		String hql2 = " inner join rider on taxinetusers.userid = rider.riderid";
		String hql3 = "	inner join driver on taxinetusers.userid = driver.driverid";
		String hql4 = "	inner join company on taxinetusers.companyid = company.companyid";
		String hql5 = "	where taxinetusers.username = :username and taxinetusers.email =:email";
		String hqlQuery = hql.concat(hql1).concat(hql2).concat(hql3).concat(hql4).concat(hql5);
		Query query = session.createQuery(hqlQuery);
		query.setParameter("userName", username.toLowerCase());
		query.setParameter("email", email);
		List<TaxiNetUsers> listUsers = query.list();
		if (listUsers.size() > 0) {
			return listUsers;
		} else
			return new ArrayList<TaxiNetUsers>();

	}

	public List<TaxiNetUsers> loginAuth(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TaxiNetUsers> paginationList(int page, int numberOfElement) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from TaxiNetUsers";
		Query query = session.createQuery(hql);
		query.setFirstResult((page-1)*numberOfElement + 1);
		query.setMaxResults(numberOfElement);
		List<TaxiNetUsers> result;
		result = query.list();
		return result;
	}	
}
