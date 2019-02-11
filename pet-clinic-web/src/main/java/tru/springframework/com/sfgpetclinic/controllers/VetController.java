package tru.springframework.com.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tru.springframework.com.sfgpetclinic.model.Vet;
import tru.springframework.com.sfgpetclinic.services.VetService;

import java.util.Set;

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
    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson(){



        return vetService.findAll();

    }
}
