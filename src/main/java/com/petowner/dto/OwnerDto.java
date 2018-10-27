package com.petowner.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.petowner.entity.Owner;

public class OwnerDto {

  private String firstName;
  private String lastName;
  private String city;
  private List<Long> petIds;
  private Long id;

  public OwnerDto(Owner owner) {
    id = owner.getId();
    firstName = owner.getFirstName();
    lastName = owner.getLastName();
    city = owner.getCity();
    if (owner.getPets() != null) {
      petIds = owner.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList());  
    } else {
      petIds = null;
    }
    
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Object getPetIds() {
    return petIds;
  }

  public void setPetIds(List<Long> petIds) {
    this.petIds = petIds;
  }

}
