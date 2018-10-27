package com.petowner.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.petowner.controller.OwnerController;
import com.petowner.entity.Owner;
import com.petowner.entity.Pet;
import com.petowner.repository.OwnerRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private OwnerRepository ownerRepository;
  
  @Before
  public void setUp() {

  }

  @Test
  public void testOwnerWithoutPets() throws Exception {

    when(this.ownerRepository.findAll()).thenReturn(Arrays.asList(
    		new Owner(1L, "Some1", "body1",  "city1"), 
    		new Owner(2L, "Some2", "body2",  "city2"),
    		new Owner(3L, "Some3", "body3",  "city3")
    		));

    mvc.perform(get("/owners").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(1))
       .andExpect(jsonPath("$[0].firstName").value("Some1"))
       .andExpect(jsonPath("$[0].lastName").value("body1"))
       .andExpect(jsonPath("$[0].city").value("city1"))
    
      .andExpect(jsonPath("$[1].id").value(2))
      .andExpect(jsonPath("$[1].firstName").value("Some2"))
      .andExpect(jsonPath("$[1].lastName").value("body2"))
      .andExpect(jsonPath("$[1].city").value("city2"))
      
      .andExpect(jsonPath("$[2].id").value(3))
      .andExpect(jsonPath("$[2].firstName").value("Some3"))
      .andExpect(jsonPath("$[2].lastName").value("body3"))
      .andExpect(jsonPath("$[2].city").value("city3"));
       
  }
  
  @Test
  public void testOwnerWithPets() throws Exception {

    Owner owner1 = new Owner(1L, "Some1", "body1",  "city1");
    Owner owner2 = new Owner(2L, "Some2", "body2",  "city2");
    Owner owner3 = new Owner(3L, "Some3", "body3",  "city3");
    Pet pet1 = new Pet(1l, "pet1", "16/05/1999", owner1);
    Pet pet2 = new Pet(2l, "pet2", "16/06/1999", owner2);
    Pet pet3 = new Pet(3l, "pet3", "16/07/1999", owner3);
    
    when(this.ownerRepository.findAll()).thenReturn(Arrays.asList(owner1, owner2, owner3));

    mvc.perform(get("/owners").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(1))
       .andExpect(jsonPath("$[0].firstName").value("Some1"))
       .andExpect(jsonPath("$[0].lastName").value("body1"))
       .andExpect(jsonPath("$[0].city").value("city1"))
    
      .andExpect(jsonPath("$[1].id").value(2))
      .andExpect(jsonPath("$[1].firstName").value("Some2"))
      .andExpect(jsonPath("$[1].lastName").value("body2"))
      .andExpect(jsonPath("$[1].city").value("city2"))
      
      .andExpect(jsonPath("$[2].id").value(3))
      .andExpect(jsonPath("$[2].firstName").value("Some3"))
      .andExpect(jsonPath("$[2].lastName").value("body3"))
      .andExpect(jsonPath("$[2].city").value("city3"));
       
  }
}
