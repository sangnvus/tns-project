package vn.co.taxinet.bean.feeagent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import vn.co.taxinet.bo.AgentBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.dto.AccountTransactionDTO;
import vn.co.taxinet.orm.AccountTransaction;
import vn.co.taxinet.utils.Utility;

@ManagedBean(name = "feeAgentChargingInvoicesController", eager = true)
@SessionScoped
public class FeeAgantChargingInvoiceBean {

	private Date fromDate;
	private Date toDate;
	private String accountSearchName;
	private String txtFromDate;
	private String txtToDate;
	private LazyDataModel<AccountTransactionDTO> lazyDataModel;
	private List<AccountTransactionDTO> listDTO;

	@ManagedProperty(value = "#{agentBO}")
	private AgentBO agentBO;

	public void initData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			// fromDate = Utility.getCurrentDateTime();
			// toDate = Utility.getCurrentDateTime();
//			searchInvoice();
			fromDate = null;
			toDate = null;
		}
	}

	public void searchInvoice() {
		System.out.println("a");
		lazyDataModel = new LazyDataModel<AccountTransactionDTO>() {

			@Override
			public List<AccountTransactionDTO> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				// TODO Auto-generated method stub
				int pageIndex = first;
				pageSize = Constants.PAGE_SIZE;
				listDTO = agentBO.listAccountTransaction(accountSearchName,
						pageIndex, pageSize, fromDate, toDate);
				return listDTO;
			}
		};
		lazyDataModel.setRowCount(4);
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

	public LazyDataModel<AccountTransactionDTO> getLazyDataModel() {
		return lazyDataModel;
	}

}
