package com.genbook.petowner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.genbook.petowner.entity.Owner;
import com.genbook.petowner.entity.Pet;
import com.genbook.petowner.repository.OwnerRepository;
import com.genbook.petowner.repository.PetRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PetRepositoryTest {

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Test
  public void testRepositoryIsPopulated() {

    List<Pet> findAll = petRepository.findAll();

    assertThat(findAll).hasSize(4);

    assertThat(findAll.get(0).getOwner().getId().equals(-1L));
  }

  @Test
  public void testRepositoryCreatePet() {

    assertThat(petRepository.findAll()).hasSize(4);

    Owner owner1 = ownerRepository.findById(-1L).get();
    Pet pet1 = new Pet("pet1", "16/05/1999", owner1);

    Pet saved = petRepository.save(pet1);
    
    Pet expectedSavedPet = new Pet(1L, "pet1", "16/05/1999", owner1);
    assertThat(expectedSavedPet).isEqualTo(saved);

    assertThat(petRepository.findAll()).hasSize(5);
  }
}
