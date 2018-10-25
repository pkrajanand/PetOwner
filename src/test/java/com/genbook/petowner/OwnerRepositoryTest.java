package com.genbook.petowner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.genbook.petowner.entity.Owner;
import com.genbook.petowner.repository.OwnerRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class OwnerRepositoryTest {

  @Autowired
  private OwnerRepository ownerRepository;

  @Test
  public void testRepositoryIsPopulated() {

    assertThat(ownerRepository.findAll()).hasSize(3);
  }

  @Test
  public void testRepositoryCreateOwner() {

		assertThat(ownerRepository.findAll()).hasSize(3);

		Owner owner = new Owner("Some1", "body1",  "city1");
		
		assertThat(ownerRepository.save(owner).equals(new Owner(1L, "Some1", "body1",  "city1")));
		
		assertThat(ownerRepository.findAll()).hasSize(4);
  }

  @Test
  public void testRepositoryGetOneOwner() {
  	
		assertThat(ownerRepository.findById(-1L).get().getFirstName().equals("Some1"));
		
  }

  @Test
  public void testRepositoryDeleteOwner() {

		assertThat(ownerRepository.findAll()).hasSize(3);

		ownerRepository.deleteById(-1L);
		
		assertThat(ownerRepository.findAll()).hasSize(2);
  }

}
