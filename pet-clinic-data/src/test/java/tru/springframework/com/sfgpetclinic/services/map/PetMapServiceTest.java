package tru.springframework.com.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tru.springframework.com.sfgpetclinic.model.Pet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PetMapServiceTest {

    private PetMapService petMapService;

    private final long petId= 1l;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();

        assertEquals(1,pets.size());
    }

    @Test
    void findById() {
        Pet foundPet = petMapService.findById(petId);

        assertEquals(petId ,foundPet.getId().longValue());
    }

    @Test
    void save() {
        petMapService.save(Pet.builder().id(2l).build());
        Set<Pet> pets = petMapService.findAll();

        assertEquals(2,pets.size());
    }

    @Test
    void delete() {
        Pet savedPet = petMapService.findById(petId);
        petMapService.delete(savedPet);

        assertNull(petMapService.findById(petId));
    }

    @Test
    void deleteByID() {
        petMapService.deleteByID(petId);
    }
}