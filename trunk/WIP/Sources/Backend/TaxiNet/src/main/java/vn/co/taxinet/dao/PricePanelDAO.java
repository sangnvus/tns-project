package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.PricePanel;

public interface PricePanelDAO extends BaseDAO {
	/**
	 * @author Ecchi
	 * @param carModel
	 * @param companyId
	 * @return price panel
	 */
	PricePanel selectPricePanel(String carModel, String companyId);

	/**
	 * @author Ecchi
	 * @param companyID
	 * @param currentDateTime
	 * @return
	 */
	int countPricePanel(int companyID, String currentDateTime);

	/**
	 * @author Ecchi
	 * @param companyID
	 * @param currentDateTime
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<PricePanel> getAllPricePanel(int companyID, String currentDateTime,
			int pageIndex, int pageSize);
}
