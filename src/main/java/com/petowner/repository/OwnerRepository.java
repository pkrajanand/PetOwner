package com.petowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petowner.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
