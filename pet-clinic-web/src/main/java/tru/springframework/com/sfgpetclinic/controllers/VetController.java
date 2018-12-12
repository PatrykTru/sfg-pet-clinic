package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tru.springframework.com.sfgpetclinic.services.VetService;

@Controller
public class VetController {

    VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"","/vets","/vets.html","/vets/index","/vets/index.html", "vets.html/index","/vets.html/index.html"})
    public String listVets(Model model){

        model.addAttribute("vets" ,vetService.findAll());

        return "vets/index";
    }

}
