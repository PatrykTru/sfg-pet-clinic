package tru.springframework.com.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.repositories.OwnerRepository;
import tru.springframework.com.sfgpetclinic.repositories.PetRepository;
import tru.springframework.com.sfgpetclinic.repositories.PetTypeRepository;
import tru.springframework.com.sfgpetclinic.services.OwnerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners ::add);
            return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {

        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {

        ownerRepository.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        ownerRepository.deleteById(id);

    }
}
