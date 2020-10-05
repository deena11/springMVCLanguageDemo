package com.example.paymytax.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.paymytax.entity.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer>{

}
