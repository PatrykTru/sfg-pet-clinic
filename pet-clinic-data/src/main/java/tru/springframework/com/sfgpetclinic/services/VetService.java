package tru.springframework.com.sfgpetclinic.services;

import tru.springframework.com.sfgpetclinic.model.Vet;

import java.util.Set;


public interface VetService {

    Vet findById(long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}