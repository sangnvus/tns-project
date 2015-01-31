/**
 * 
 */
package vn.co.taxinet.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.dao.AddressDAO;
import vn.co.taxinet.dao.impl.BaseDAOImpl;
import vn.co.taxinet.orm.Address;

/**
 * @author DEV
 *
 */
@Service("addressDAO")
@Transactional
public class AddressDAOImpl extends BaseDAOImpl implements AddressDAO {

	private static final Logger log = LogManager.getLogger(AddressDAOImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8588415018253127469L;

	public List<Address> getAddresses(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
