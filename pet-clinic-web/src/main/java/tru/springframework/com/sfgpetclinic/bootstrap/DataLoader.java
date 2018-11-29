package tru.springframework.com.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tru.springframework.com.sfgpetclinic.model.Owner;
import tru.springframework.com.sfgpetclinic.model.Vet;
import tru.springframework.com.sfgpetclinic.services.OwnerService;
import tru.springframework.com.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    public final OwnerService ownerService;
    public final VetService vetService;


    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Gleneae");

        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Pet");
        vet2.setLastName("Curer");

        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}
