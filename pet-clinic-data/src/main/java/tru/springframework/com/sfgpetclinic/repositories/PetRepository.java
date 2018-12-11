package tru.springframework.com.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.sfgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet,Long> {

}
