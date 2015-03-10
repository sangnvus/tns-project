package vn.co.taxinet.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bo.CompanyBO;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.CompanyDTO;
import vn.co.taxinet.dto.DriverDTO;
import vn.co.taxinet.orm.Company;

@RestController
@RequestMapping("/CompanyController")
public class CompanyController {

	@Autowired
	private CompanyBO companyBO;

	@RequestMapping(value = "/findCompanyByDriverId", method = RequestMethod.GET)
	public CompanyDTO findCompanyByDriverId(
			@RequestParam Map<String, String> requestParams) throws TNException {
		String id = requestParams.get("id");
		CompanyDTO companyDTO = companyBO.findCompanyByDriverId(id);
		return companyDTO;
	}
}
