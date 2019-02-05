package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tru.springframework.com.sfgpetclinic.model.Pet;
import tru.springframework.com.sfgpetclinic.model.Visit;
import tru.springframework.com.sfgpetclinic.services.PetService;
import tru.springframework.com.sfgpetclinic.services.VisitService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder binder)
    {
        binder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Map<String,Object> model)
    {
        Pet pet = petService.findById(petId);
        model.put("pet" ,pet);
        Visit visit = new Visit();
        visit.setPet(pet);
        pet.getVisits().add(visit);

        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisit (@PathVariable Long petId , Map<String,Object> model){
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisit(@Valid Visit visit, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "pets/createOrUpdateVisitForm";
        }
        else
        {
            visitService.save(visit);
            return "redirect:/owners/{ownersId}";
        }
    }
}
