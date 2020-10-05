package com.example.paymytax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Zone implements Comparable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	
	private String zoneName;

	public Zone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zone(int id, String zoneName) {
		super();
		Id = id;
		this.zoneName = zoneName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((zoneName == null) ? 0 : zoneName.hashCode());
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
		Zone other = (Zone) obj;
		if (Id != other.Id)
			return false;
		if (zoneName == null) {
			if (other.zoneName != null)
				return false;
		} else if (!zoneName.equals(other.zoneName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Zone [Id=" + Id + ", zoneName=" + zoneName + "]";
	}

	@Override
	public int compareTo(Object o) {
		Zone obj = (Zone) o;
		return (this.getId()<obj.getId()?-1:this.getId()>obj.getId()?1:0);
	}
	
	
	
	
	

}
