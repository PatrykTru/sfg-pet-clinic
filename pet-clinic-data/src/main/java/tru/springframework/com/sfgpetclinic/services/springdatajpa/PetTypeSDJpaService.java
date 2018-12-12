package tru.springframework.com.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tru.springframework.com.sfgpetclinic.model.PetType;
import tru.springframework.com.sfgpetclinic.repositories.PetTypeRepository;
import tru.springframework.com.sfgpetclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    PetTypeRepository petTypeRepository;


    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;

    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);

    }

    @Override
    public void deleteByID(Long id) {
        petTypeRepository.deleteById(id);
    }
}