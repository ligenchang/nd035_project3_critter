package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.getAllByCustomerId(ownerId);
    }

    public Pet savePat(Pet pet, long ownerId){
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        Pet mypet = petRepository.save(pet);
        customer.addPet(mypet);
        customerRepository.save(customer);
        return mypet;
    }
}
