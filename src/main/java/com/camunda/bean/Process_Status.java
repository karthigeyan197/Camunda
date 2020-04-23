package com.camunda.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "process_status", uniqueConstraints={@UniqueConstraint(columnNames={"msisdn"})})
public class Process_Status {
	
	@Column(name = "custId", length=20, nullable=false, unique=true)
	private String custId;

	@Id
	@Column(name = "msisdn", length=20, nullable=false)
	private String msisdn;

	@Column(name="cmreqid", length=100, nullable=true)
	private String cmreqid;

	@Column(name="cmstatus", length=100, nullable=true)
	private String cmstatus;
	
	public Process_Status() {
		
	}

	@Override
	public String toString() {
		return "Process_Status [custId=" + custId + ", msisdn=" + msisdn + ", cmreqid=" + cmreqid + ", cmstatus="
				+ cmstatus + "]";
	}

	public Process_Status(String custId, String msisdn, String cmreqid, String cmstatus) {
		super();
		this.custId = custId;
		this.msisdn = msisdn;
		this.cmreqid = cmreqid;
		this.cmstatus = cmstatus;
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

	public String getCmreqid() {
		return cmreqid;
	}

	public void setCmreqid(String cmreqid) {
		this.cmreqid = cmreqid;
	}

	public String getCmstatus() {
		return cmstatus;
	}

	public void setCmstatus(String cmstatus) {
		this.cmstatus = cmstatus;
	}
	
	

}
