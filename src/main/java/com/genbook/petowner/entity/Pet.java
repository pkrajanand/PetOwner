package com.genbook.petowner.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties("new")
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends AbstractPersistable<Long> {

  @NonNull
  private String name;
  private String birthday;

  @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
  @JsonIdentityReference(alwaysAsId=true)
  @JsonProperty("ownerId")
  @ManyToOne(cascade=CascadeType.PERSIST)
  private Owner owner;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }
  
  
}
