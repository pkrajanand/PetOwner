package com.genbook.petowner.dto;

import java.util.stream.Collectors;

import com.genbook.petowner.entity.Owner;

public class OwnerDto {

  private String firstName;
  private String lastName;
  private String city;
  private Object petIds;
  private Long id;

  public OwnerDto(Owner owner) {
    id = owner.getId();
    firstName = owner.getFirstName();
    lastName = owner.getLastName();
    city = owner.getCity();
    petIds = owner.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList());
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

  public void setPetIds(Object petIds) {
    this.petIds = petIds;
  }

}
