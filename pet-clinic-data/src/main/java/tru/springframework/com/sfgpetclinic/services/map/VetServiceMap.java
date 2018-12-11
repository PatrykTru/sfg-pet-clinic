package tru.springframework.com.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import tru.springframework.com.sfgpetclinic.model.Specialty;
import tru.springframework.com.sfgpetclinic.model.Vet;
import tru.springframework.com.sfgpetclinic.services.SpecialtyService;
import tru.springframework.com.sfgpetclinic.services.VetService;

import java.util.Set;
@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if(object.getSpecialties().size()> 0) {
            object.getSpecialties().forEach(specialty -> {
                if(specialty.getId()== null) {
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteById(id);
    }
}