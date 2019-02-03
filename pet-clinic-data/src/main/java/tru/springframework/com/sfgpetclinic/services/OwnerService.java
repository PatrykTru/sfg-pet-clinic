package tru.springframework.com.sfgpetclinic.services;

import tru.springframework.com.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);


}
