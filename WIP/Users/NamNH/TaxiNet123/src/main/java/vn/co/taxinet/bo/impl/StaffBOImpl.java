package vn.co.taxinet.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.co.taxinet.bo.StaffBO;

@Service(value="staffBO")
@Transactional
public class StaffBOImpl implements StaffBO {
	private static final Logger logger = LogManager.getLogger(StaffBOImpl.class);
}
