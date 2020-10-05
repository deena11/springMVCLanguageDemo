package com.example.paymytax.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.paymytax.enums.userStatus;

@Entity
public class UAV implements Comparable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;
	
	@Enumerated(EnumType.STRING)
	private userStatus status;
	
	@OneToOne
	private Zone zone;
	
	private Double value;
	
	

	public UAV() {
	}



	public UAV(int id, String description, userStatus status, Zone zone, Double value) {
		this.id = id;
		this.description = description;
		this.status = status;
		this.zone = zone;
		this.value = value;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public userStatus getStatus() {
		return status;
	}



	public void setStatus(userStatus status) {
		this.status = status;
	}



	public Zone getZone() {
		return zone;
	}



	public void setZone(Zone zone) {
		this.zone = zone;
	}



	public Double getValue() {
		return value;
	}



	public void setValue(Double value) {
		this.value = value;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UAV other = (UAV) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "UAV [id=" + id + ", description=" + description + ", status=" + status + ", zone=" + zone + ", value="
				+ value + "]";
	}



	@Override
	public int compareTo(Object o) {
		UAV obj = (UAV) o;
		return (this.getId()<obj.getId()?-1:this.getId()>obj.getId()?1:0);
	}
	
	
	
	
}
