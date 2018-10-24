package com.genbook.petowner.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends AbstractPersistable<Long> {

  @NonNull
  private String firstName;
  private String lastName;
  private String city;
  

}
