package tru.springframework.com.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner,Long> {

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);

}
