package com.petowner.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.petowner.entity.Owner;
import com.petowner.entity.Pet;
import com.petowner.repository.OwnerRepository;
import com.petowner.repository.PetRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PetRepositoryTest {

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Test
  public void testRepositoryFetch() {

    List<Pet> findAll = petRepository.findAll();

    assertThat(findAll).hasSize(4);
  }

  @Test
  public void testRepositoryCreatePet() {

    assertThat(petRepository.findAll()).hasSize(4);

    Owner owner1 = ownerRepository.findById(999999L).get();
    Pet pet1 = new Pet("pet1", "16/05/1999", owner1);

    Pet saved = petRepository.save(pet1);
    
    assertThat(saved.getBirthday()).isEqualTo("16/05/1999");
    assertThat(saved.getName()).isEqualTo("pet1");
    assertThat(saved.getOwner().getId()).isEqualTo(999999L);
    assertThat(saved.getId()).isNotNull();

    assertThat(petRepository.findAll()).hasSize(5);
  }
}
