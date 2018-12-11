package tru.springframework.com.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.sfgpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
