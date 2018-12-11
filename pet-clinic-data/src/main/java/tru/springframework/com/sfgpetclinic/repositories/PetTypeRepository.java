package tru.springframework.com.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.sfgpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
