package vn.co.taxinet.bean.feeagent;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.utils.Utility;

@ManagedBean(name="feeAgentChargingInvoicesController",eager=true)
@SessionScoped
public class FeeAgantChargingInvoiceBean {
	
	private Date fromDate;
	private Date toDate;
	private String accountSearchName;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private String txtFromDate;
	private String txtToDate;
	
	@ManagedProperty(value="#{agentBO}")
	private AgentBO agentBO;
	
	public void initData(){
		if (!FacesContext.getCurrentInstance().isPostback()) {
			fromDate = Utility.getCurrentDateTime();
			toDate = Utility.getCurrentDateTime();
			
		}
	}
	
	public void searchInvoice(){
//		System.out.println("a");
		agentBO.listAccountTransaction(accountSearchName, 1, 4,fromDate,toDate);
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

	public String getAccountSearchName() {
		return accountSearchName;
	}

	public void setAccountSearchName(String accountSearchName) {
		this.accountSearchName = accountSearchName;
	}
	public AgentBO getAgentBO() {
		return agentBO;
	}
	public void setAgentBO(AgentBO agentBO) {
		this.agentBO = agentBO;
	}
	public String getTxtFromDate() {
		return txtFromDate;
	}
	public void setTxtFromDate(String txtFromDate) {
		this.txtFromDate = txtFromDate;
	}
	public String getTxtToDate() {
		return txtToDate;
	}
	public void setTxtToDate(String txtToDate) {
		this.txtToDate = txtToDate;
	}
	
	
}
