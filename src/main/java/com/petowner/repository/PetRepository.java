package com.petowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petowner.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
