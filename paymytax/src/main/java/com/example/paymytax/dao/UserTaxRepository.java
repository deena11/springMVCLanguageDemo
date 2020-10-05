package com.example.paymytax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.paymytax.entity.UserTax;

@Repository
public interface UserTaxRepository extends JpaRepository<UserTax, Integer>{
	
	@Query(value = "SELECT * FROM User_Tax where year_of_assessment=?", nativeQuery=true)
	public List<UserTax> getUserTaxByYear(int year);
	
	/*totally failed look into it at the end
	@Query(value = "SELECT new com.example.paymytax.dto.ReportData(zone_id,status,sum(total_tax))"+" FROM UserTax where year_of_assessment=? group by status,zone_id", nativeQuery=true )
	public List<ReportData> getReport(int year);
	*/
}
