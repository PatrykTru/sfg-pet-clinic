package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.services.OwnerService;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;
    @InitBinder
    public void setAllowedFields(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping("/find")
    public String findOwners(Model model){

        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        if(owner.getLastName()==null)
            owner.setLastName("");

        List<Owner> resoults = ownerService.findAllByLastNameLike(owner.getLastName());

        if(resoults.isEmpty())
        {
            result.rejectValue("lastName" ,"notFound" , "not found");
            return "owners/findOwners";
        }
        else if(resoults.size()==1)
        {
            owner = resoults.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else {
            model.addAttribute("selections" , resoults);

            return "owners/ownersList";

        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){

            ModelAndView mav = new ModelAndView("owners/ownerDetails");
            mav.addObject(ownerService.findById(ownerId));
            return mav;
    }
}
