package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.model.PetType;
import tru.springframework.com.sfgpetclinic.services.OwnerService;
import tru.springframework.com.sfgpetclinic.services.PetService;
import tru.springframework.com.sfgpetclinic.services.PetTypeService;

import java.util.Set;

@Controller
@RequestMapping("owners/{ownersId}")
public class PetController {


    PetService petService;
    OwnerService ownerService;
    PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes()
    {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownersId)
    {
        return ownerService.findById(ownersId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder binder){
    binder.setDisallowedFields("id");

    }


}
