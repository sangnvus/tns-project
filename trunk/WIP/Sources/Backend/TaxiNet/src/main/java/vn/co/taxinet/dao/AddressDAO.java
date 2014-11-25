/**
 * 
 */
package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.Address;

/**
 * @author DEV
 *
 */
public interface AddressDAO {

	List<Address> getAddresses(Address address);
}
