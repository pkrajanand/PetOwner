package com.genbook.petowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genbook.petowner.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
