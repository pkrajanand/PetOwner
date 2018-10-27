package com.petowner.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties("new")
@RequiredArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends AbstractPersistable<Long> {

  @NonNull
  private String firstName;
  private String lastName;
  private String city;
  
  @OneToMany(fetch = FetchType.LAZY,
      mappedBy = "owner", cascade=CascadeType.ALL)
  private List<Pet> pets;

  public Owner() {}
  
  public Owner(Long id, String firstName, String lastName, String city) {
    super();
    this.setId(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
  }

  public Owner(String firstName, String lastName, String city) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
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

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }
  
  
}
