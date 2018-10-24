package com.genbook.petowner.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@Table(name = "pets")
public class Pet extends AbstractPersistable<Long> {

  @NonNull
  private String name;
  private String birthday;

  @ManyToOne(cascade=CascadeType.PERSIST)
  private Owner owner;
  
}
