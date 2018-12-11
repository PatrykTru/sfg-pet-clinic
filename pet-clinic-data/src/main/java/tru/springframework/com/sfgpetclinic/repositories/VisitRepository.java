package tru.springframework.com.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.sfgpetclinic.model.Visit;

public interface VisitRepository  extends CrudRepository<Visit,Long> {
}
