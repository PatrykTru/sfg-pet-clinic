package tru.springframework.com.sfgpetclinic.services;

import tru.springframework.com.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
