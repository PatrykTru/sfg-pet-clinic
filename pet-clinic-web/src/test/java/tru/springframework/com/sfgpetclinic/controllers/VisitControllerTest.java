package tru.springframework.com.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.model.Pet;
import tru.springframework.com.sfgpetclinic.model.PetType;
import tru.springframework.com.sfgpetclinic.services.PetService;
import tru.springframework.com.sfgpetclinic.services.VisitService;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;
    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;
    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        Long petId =1l;
        Long ownerId = 1l;
        when(petService.findById(anyLong()))
                .thenReturn(Pet.builder()
                        .id(petId)
                        .birthDate(LocalDate.of(2018,11,13))
                        .name("Chester")
                        .visits(new HashSet<>())
                        .owner(Owner.builder()
                                .id(ownerId)
                                .firstName("John")
                                .lastName("Doe")
                                .build())
                        .petType(PetType.builder()
                                .name("Dog")
                                .build())
                        .build());

        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();


    }

    @Test
    void initNewVisit() throws Exception {

        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

    }

    @Test
    void processNewVisit()throws Exception {

        mockMvc.perform(post(visitsUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date","2018-11-11")
                .param("description" ,"Yet another Visit"))

                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"));


    }
}