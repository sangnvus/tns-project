/**
 * 
 */
package vn.co.taxinet.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.context.annotation.Scope;

/**
 * @author DEV
 *
 */
@ManagedBean(name="errorForm")
@RequestScoped
public class ErrorBean extends BaseBean {

	public String back() {
		return ("home");
	}
}
