package vn.co.taxinet.bean.rider;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.component.password.Password;

import vn.co.taxinet.bo.AuthenticationBO;
import vn.co.taxinet.bo.RiderBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.orm.Rider;

@ManagedBean(name = "riderPaymentController", eager = true)
@SessionScoped
public class RiderPaymentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495680425632888301L;
	// properties for register page
		private String city;
		private String paymentMethod;
		private Date fromDate;
		private Date toDate;

		@ManagedProperty(value = "#{riderBO}")
		private RiderBO riderBO;
		

		private String UserID ="2" ;
		private String password="2" ;
		private String Email="2" ;
		private Rider userAccount;

		// private TaxiNetUserDAO taxiNetUserDAO;
		/**
		 * init load
		 */		
		public void init() {
			if (!FacesContext.getCurrentInstance().isPostback()) {
				userAccount = new Rider();
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession session = request.getSession();
//				UserID = session.getAttribute("UserID").toString();
//				Email = session.getAttribute("Email").toString();
//				password = session.getAttribute("Password").toString();
				// If UserID doesnt exist, redirect to login page
				if (null == UserID || null == Email) {
					try {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/TN/faces/Login.xhtml");
					} catch (IOException e) {
						e.getMessage();
						e.printStackTrace();
					}
				} else {
					// if UserID exist, find Rider by UserID
					userAccount = riderBO.findByID(UserID);
					// if cant find Rider. redirect to login page
					if (userAccount == null) {
						try {
							FacesContext.getCurrentInstance().getExternalContext()
									.redirect("/TN/faces/Login.xhtml");
						} catch (IOException e) {
							e.getMessage();
							e.printStackTrace();
						}
					} else {
						city = "";
						paymentMethod = "";
						Calendar date = Calendar.getInstance();
						fromDate = date.getTime();
						toDate = date.getTime();
					}
				}

			}
		}
		
		public String doTripsSearch(){
			return city;
		}

		//getter & setter

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public Date getFromDate() {
			return fromDate;
		}

		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		public Date getToDate() {
			return toDate;
		}

		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

		public RiderBO getRiderBO() {
			return riderBO;
		}

		public void setRiderBO(RiderBO riderBO) {
			this.riderBO = riderBO;
		}
	
}
