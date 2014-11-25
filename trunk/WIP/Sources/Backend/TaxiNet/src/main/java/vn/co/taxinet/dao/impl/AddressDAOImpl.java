/**
 * 
 */
package vn.co.taxinet.dao.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.AddressDAO;
import vn.co.taxinet.dao.impl.BaseDAOImpl;
import vn.co.taxinet.orm.Address;

/**
 * @author DEV
 *
 */
@Transactional
public class AddressDAOImpl extends BaseDAOImpl implements AddressDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8588415018253127469L;

	public List<Address> getAddresses(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
