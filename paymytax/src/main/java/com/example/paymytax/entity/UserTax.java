package com.example.paymytax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.example.paymytax.enums.userStatus;

@Entity
public class UserTax implements Comparable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int yearOfAssessment;
	
	@Size(min = 2, max = 20)
	private String name;
	
	@Email
	private String eMail;
	
	@Size(min = 10, max = 200)
	private String address;
	
	@OneToOne
	private Zone zone;
	
	private String description;
	
	private userStatus status;
	
	@NotNull
	private int constructedYear;
	
	@NotNull
	private int area;
	
	
	private Double totalTax;

	public UserTax() {
	}

	public UserTax(int id, int yearOfAssessment, String name, String eMail, String address, Zone zone,
			String description, userStatus status, int constructedYear, int area, double totalTax) {
		this.id = id;
		this.yearOfAssessment = yearOfAssessment;
		this.name = name;
		this.eMail = eMail;
		this.address = address;
		this.zone = zone;
		this.description = description;
		this.status = status;
		this.constructedYear = constructedYear;
		this.area = area;
		this.totalTax = totalTax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYearOfAssessment() {
		return yearOfAssessment;
	}

	public void setYearOfAssessment(int yearOfAssessment) {
		this.yearOfAssessment = yearOfAssessment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
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

	public int getContsructedYear() {
		return constructedYear;
	}

	public void setConstructedYear(int constructedYear) {
		this.constructedYear = constructedYear;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + area;
		result = prime * result + constructedYear;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalTax == null) ? 0 : totalTax.hashCode());
		result = prime * result + yearOfAssessment;
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
		UserTax other = (UserTax) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area != other.area)
			return false;
		if (constructedYear != other.constructedYear)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		if (totalTax == null) {
			if (other.totalTax != null)
				return false;
		} else if (!totalTax.equals(other.totalTax))
			return false;
		if (yearOfAssessment != other.yearOfAssessment)
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
		return "UserTax [id=" + id + ", yearOfAssessment=" + yearOfAssessment + ", name=" + name + ", eMail=" + eMail
				+ ", address=" + address + ", zone=" + zone + ", description=" + description + ", status=" + status
				+ ", contsructedYear=" + constructedYear + ", area=" + area + ", totalTax=" + totalTax + "]";
	}

	@Override
	public int compareTo(Object o) {
		UserTax obj = (UserTax) o;
		return (this.getId()<obj.getId()?-1:this.getId()>obj.getId()?1:0);
	}
	
	
	
	

}
