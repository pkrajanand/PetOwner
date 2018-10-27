package com.petowner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petowner.dto.OwnerDto;
import com.petowner.entity.Owner;
import com.petowner.mapper.OwnerMapper;
import com.petowner.repository.OwnerRepository;


@RestController
@CrossOrigin
public class OwnerController {

  @Autowired
  private OwnerRepository ownerRepository;
	
  @GetMapping("/owners")
  public @ResponseBody List<OwnerDto> get() {

    List<Owner> owners = ownerRepository.findAll();
    owners.stream().forEach(owner -> owner.getPets());
    return OwnerMapper.map(owners);

  }

}
