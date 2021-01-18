package com.excelra.glpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.ResourceAllocation;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation, String> {

	ResourceAllocation findByUId(String uId);

}
