package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.services.OwnerService;

import javax.validation.Valid;
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

        List<Owner> resoults = ownerService.findAllByLastNameLike("%" + owner.getLastName() +"%");

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

    @GetMapping("/new")
    public String initCreationForm(Model model)
    {

        model.addAttribute("owner" , Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner , BindingResult result)
    {
        if(result.hasErrors())
        {
            return "owners/createOrUpdateOwnerForm";
        }
        else
        {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+ savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId,Model model)
    {

        model.addAttribute("owner",ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable Long ownerId , @Valid Owner owner , BindingResult result,Model model)
    {
        if(result.hasErrors())
        {
            return "owners/createOrUpdateOwnerForm";
        }
        else
        {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            model.addAttribute(savedOwner);

        }

        return "redirect:/owners/"+ ownerId;

    }

}
