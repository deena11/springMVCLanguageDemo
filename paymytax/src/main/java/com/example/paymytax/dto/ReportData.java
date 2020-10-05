package com.example.paymytax.dto;

import com.example.paymytax.entity.Zone;
import com.example.paymytax.enums.userStatus;

public class ReportData {

	
	private Zone zone;
	
	//need to change the name of enum dont forget
	private userStatus status;
	
	private Long result;

	public ReportData(Zone zone, userStatus status, Long result) {
		super();
		this.zone = zone;
		this.status = status;
		this.result = result;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public userStatus getStatus() {
		return status;
	}

	public void setStatus(userStatus status) {
		this.status = status;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ReportData [zone=" + zone + ", status=" + status + ", result=" + result + "]";
	}
	
	
	
}
