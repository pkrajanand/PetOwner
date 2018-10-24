package com.genbook.petowner.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.genbook.petowner.entity.Owner;
import com.genbook.petowner.entity.Pet;
import com.genbook.petowner.repository.OwnerRepository;
import com.genbook.petowner.repository.PetRepository;

@RestController
@CrossOrigin
public class PetController {

  private final Logger log = LoggerFactory.getLogger(PetController.class);

  @Autowired
  private PetRepository petRepository;
  
  @Autowired
  OwnerRepository ownerRepository;
	
  @GetMapping("/pets")
  public @ResponseBody List<Pet> get() {

    List<Pet> findAll = petRepository.findAll();
    return findAll;

  }

  @PostMapping("/pets")
  public ResponseEntity<Pet> create(@Valid @RequestBody Pet pet) throws URISyntaxException {
    
    log.info("Request to create group: {}", pet);

    Optional<Owner> owner = ownerRepository.findById(pet.getOwner().getId());
    if (owner.isPresent()) {
      pet.setOwner(owner.get());
      Pet saved = petRepository.save(pet);
      
      return ResponseEntity.created(new URI("/pets" + saved.getId()))
          .body(saved);
    }
    
    return ResponseEntity.badRequest().body(pet);
  }

}
