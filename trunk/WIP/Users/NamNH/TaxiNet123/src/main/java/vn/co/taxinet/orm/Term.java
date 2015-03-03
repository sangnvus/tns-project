package vn.co.taxinet.orm;

// Generated Feb 17, 2015 1:59:07 PM by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Term generated by hbm2java
 */
public class Term implements java.io.Serializable {

	private String termId;
	private String content;
	private String userType;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;

	public Term() {
	}

	public Term(String termId, String createdBy, Date createdDate,
			String lastModifiedBy, Date lastModifiedDate) {
		this.termId = termId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Term(String termId, String content, String userType,
			String createdBy, Date createdDate, String lastModifiedBy,
			Date lastModifiedDate) {
		this.termId = termId;
		this.content = content;
		this.userType = userType;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getTermId() {
		return this.termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}