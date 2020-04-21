package com.camunda.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "msisdn", uniqueConstraints={@UniqueConstraint(columnNames={"custId"})})
public class MSISDN {
	
	@Id
	@Column(name = "custId", length=20, nullable=false, unique=true)
	private String custId;

	@Column(name = "msisdn", length=20, nullable=true)
	private String msisdn;

	@Column(name="protype", length=5, nullable=true)
	private String protype;

	@Column(name="status", length=20, nullable=true)
	private String status;
	
	public MSISDN() {
		
	}

	public MSISDN(String custId, String msisdn, String protype, String status) {
		super();
		this.custId = custId;
		this.msisdn = msisdn;
		this.protype = protype;
		this.status = status;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getProtype() {
		return protype;
	}

	public void setProtype(String protype) {
		this.protype = protype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MSISDN [custId=" + custId + ", msisdn=" + msisdn + ", protype=" + protype + ", status=" + status + "]";
	}

}
