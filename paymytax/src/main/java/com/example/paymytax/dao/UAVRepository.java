package com.example.paymytax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.paymytax.entity.UAV;

@Repository
public interface UAVRepository extends JpaRepository<UAV, Integer> {
	
	@Query(value = "Select distinct description from UAV ", nativeQuery = true)
	List<String> findAllDescriptions();
	
	@Query(value = "Select value from UAV where status=? and description=? and zone_id=?",nativeQuery = true)
	Integer getUnitAreaValue(String status,String decription,int zoneId);
}
