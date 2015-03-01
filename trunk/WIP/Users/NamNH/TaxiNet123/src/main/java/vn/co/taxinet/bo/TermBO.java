package vn.co.taxinet.bo;

import org.springframework.stereotype.Service;

import vn.co.taxinet.orm.Term;

public interface TermBO {

	/**
	 * @author Hieu-Gie
	 * 
	 * @param type
	 * @return
	 */
	public Term findTermByType(String type);
}
