package vn.co.taxinet.ws;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.co.taxinet.bo.TermBO;
import vn.co.taxinet.bo.TripBO;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.TNException;
import vn.co.taxinet.dto.MessageDTO;
import vn.co.taxinet.orm.Term;

@RestController
@RequestMapping("/TermController")
public class TermController {

	@Autowired
	private TermBO termBO;

	@RequestMapping(value = "/GetTerm", method = RequestMethod.POST)
	public Term findTermByType(@RequestParam Map<String, String> requestParams) {

		String type = requestParams.get("type");
		return termBO.findTermByType(type);
	}

}
