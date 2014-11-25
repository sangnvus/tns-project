/**
 * 
 */
package vn.co.taxinet.orm;

import java.io.Serializable;
import java.util.List;

/**
 * @author DEV
 *
 */
public class City extends BaseEntity {	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String name;
	private List<District> district;
	/**
	 * @return the district
	 */
	public List<District> getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(List<District> district) {
		this.district = district;
	}
}
