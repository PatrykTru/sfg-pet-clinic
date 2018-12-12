package tru.springframework.com.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.model.Pet;
import tru.springframework.com.sfgpetclinic.services.OwnerService;
import tru.springframework.com.sfgpetclinic.services.PetService;
import tru.springframework.com.sfgpetclinic.services.PetTypeService;

import java.util.Set;
@Service
@Profile({"default" , "map"})
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        Owner savedOwner = null;
        if(object!= null)
        {
            if(object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if(pet.getPetType()!=null)
                    {
                        pet.setPetType(petTypeService.save(pet.getPetType()));

                    }else
                      throw  new RuntimeException("Pet Type is Required");

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        }
        else
            return null;

    }

    @Override
    public void delete(Owner object) {
    super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
    super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
