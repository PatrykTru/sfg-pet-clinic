package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.model.Pet;
import tru.springframework.com.sfgpetclinic.model.PetType;
import tru.springframework.com.sfgpetclinic.services.OwnerService;
import tru.springframework.com.sfgpetclinic.services.PetService;
import tru.springframework.com.sfgpetclinic.services.PetTypeService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownersId}")
public class PetController {


    PetService petService;
    OwnerService ownerService;
    PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }



    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes()
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



    @GetMapping("/pets/new")
    public String initCreateForm(Owner owner,Model model)
    {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);

        model.addAttribute("pet",pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreateForm(@Valid Owner owner, BindingResult result, @Valid Pet pet, ModelMap model)
    {


        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (result.hasErrors())
        {
            model.put("pet",pet);
            return "pets/createOrUpdateForm";
        }
        else {


            petService.save(pet);
            return "redirect:/owners/"+ owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId,Model model)
    {
        model.addAttribute("pet" , petService.findById(petId));
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processEditForm( Owner owner, BindingResult result, @Valid Pet pet, ModelMap model)
    {

        pet.setOwner(owner);
        if (result.hasErrors()){
        model.put("pet", pet);
        return "pets/createOrUpdatePetForm";
        }
        else {


            Pet managedPet = petService.save(pet);
            owner.getPets().add(managedPet);

            return "redirect:/owners/"+ owner.getId();
        }
    }
}
