package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.co.taxinet.bo.CompanyBO;
import vn.co.taxinet.common.Constants.Message;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dao.CityNameDAO;
import vn.co.taxinet.dao.CompanyDAO;
import vn.co.taxinet.dao.TaxiNetUserDAO;
import vn.co.taxinet.dto.CompanyDTO;
import vn.co.taxinet.orm.Address;
import vn.co.taxinet.orm.City;
import vn.co.taxinet.orm.CityName;
import vn.co.taxinet.orm.Company;
import vn.co.taxinet.orm.TaxiNetUsers;

@Service
public class CompanyBOImpl implements CompanyBO {
	private static final Logger logger = LogManager
			.getLogger(CompanyBOImpl.class);
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private TaxiNetUserDAO taxiNetUserDAO;
	@Autowired
	private CityNameDAO cityNameDAO;

	public CityNameDAO getCityNameDAO() {
		return cityNameDAO;
	}

	public void setCityNameDAO(CityNameDAO cityNameDAO) {
		this.cityNameDAO = cityNameDAO;
	}

	public TaxiNetUserDAO getTaxiNetUserDAO() {
		return taxiNetUserDAO;
	}

	public void setTaxiNetUserDAO(TaxiNetUserDAO taxiNetUserDAO) {
		this.taxiNetUserDAO = taxiNetUserDAO;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public CompanyDTO findCompanyByDriverId(String id) throws TNException {
		if (id == null || id.equalsIgnoreCase("")) {
			throw new TNException(Message.NULL_PARAMS);
		}
		TaxiNetUsers taxiNetUsers = taxiNetUserDAO.findById(id);
		if (taxiNetUsers == null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}

		Company company = taxiNetUsers.getCompany();
		Address city = company.getAddress();
		if (city== null) {
			throw new TNException(Message.DATA_NOT_FOUND);
		}
		int cityId = company.getAddress().getCity().getCityId();
		String langCode = taxiNetUsers.getLanguage().getLanguageCode();

		CityName cityName = cityNameDAO.findCityNameByIdAndLanguageCode(cityId,
				langCode);
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setName(company.getName());
		companyDTO.setCity(cityName.getCityName());
		companyDTO.setAddress(company.getAddress().getAddressDetail());
		companyDTO.setPhone(company.getMobileNo());
		companyDTO.setVat_number(company.getVatnumber());
		return companyDTO;
	}

}
