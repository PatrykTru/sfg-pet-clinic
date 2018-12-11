package tru.springframework.com.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.sfgpetclinic.model.Specialty;

public interface SpecialtyRepository extends CrudRepository<Specialty,Long> {
}
