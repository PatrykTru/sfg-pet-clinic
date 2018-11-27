package tru.springframework.com.sfgpetclinic.services;

import tru.springframework.com.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);


}
