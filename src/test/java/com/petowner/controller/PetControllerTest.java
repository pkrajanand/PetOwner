package com.petowner.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.petowner.controller.PetController;
import com.petowner.entity.Owner;
import com.petowner.entity.Pet;
import com.petowner.repository.OwnerRepository;
import com.petowner.repository.PetRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class PetControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PetRepository petRepository;

  @MockBean
  private OwnerRepository ownerRepository;
  
  @Before
  public void setUp() {

  }

  @Test
  public void testList() throws Exception {

    Owner owner1 = new Owner(1L, "Some1", "body1",  "city1");
    Owner owner2 = new Owner(2L, "Some2", "body2",  "city2");
    Owner owner3 = new Owner(3L, "Some3", "body3",  "city3");
    Pet pet1 = new Pet(1l, "pet1", "16/05/1999", owner1);
    Pet pet2 = new Pet(2l, "pet2", "16/06/1999", owner2);
    Pet pet3 = new Pet(3l, "pet3", "16/07/1999", owner3);

    when(this.petRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2, pet3));

    mvc.perform(get("/pets").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(1))
       .andExpect(jsonPath("$[0].name").value("pet1"))
       .andExpect(jsonPath("$[0].birthday").value("16/05/1999"))
       .andExpect(jsonPath("$[0].ownerId").value(1L))
       .andExpect(jsonPath("$[1].id").value(2))
       .andExpect(jsonPath("$[1].name").value("pet2"))
       .andExpect(jsonPath("$[1].birthday").value("16/06/1999"))
       .andExpect(jsonPath("$[1].ownerId").value(2L))
       .andExpect(jsonPath("$[2].id").value(3))
       .andExpect(jsonPath("$[2].name").value("pet3"))
       .andExpect(jsonPath("$[2].birthday").value("16/07/1999"))
       .andExpect(jsonPath("$[2].ownerId").value(3L));
       
  }
  
  @Test
  public void testCreate() throws Exception {

    String json = "{\"id\":1,\"name\":\"pet1\", \"birthday\":\"16/05/1999\", \"ownerId\":{\"id\":\"1\"}}";

    Owner owner1 = new Owner(1L, "Some1", "body1",  "city1");
    
    Pet pet1 = new Pet(1L, "pet1", "16/05/1999", owner1);
    Pet savedPet1 = new Pet(1L, "pet1", "16/05/1999", owner1);
    
    when(this.ownerRepository.findById(pet1.getOwner().getId())).thenReturn(Optional.of(owner1));
    
    when(this.petRepository.save(pet1)).thenReturn(savedPet1);

    mvc.perform(post("/pets").content(json)
                             .contentType(APPLICATION_JSON)
                             .accept(APPLICATION_JSON))
       .andExpect(status().isCreated())
       .andExpect(jsonPath("$.id").value(1))
       .andExpect(jsonPath("$.name").value("pet1"))
       .andExpect(jsonPath("$.birthday").value("16/05/1999"))
       .andExpect(jsonPath("$.ownerId").value(1L));
  }

}
