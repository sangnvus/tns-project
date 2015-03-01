package vn.co.taxinet.dao;

import vn.co.taxinet.orm.PricePanel;

public interface PricePanelDAO extends BaseDAO {
	/**
	 * @author Ecchi
	 * @param carModel
	 * @param companyId
	 * @return price panel
	 */
	PricePanel selectPricePanel(String carModel, String companyId);
}
