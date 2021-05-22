package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer addCustomer(Customer customer, List<Long> petIds){

        List<Pet> pets = new ArrayList<>();

        if (petIds != null){
            for (Long petId : petIds){
                Pet pet = petRepository.getOne(petId);
                pets.add(pet);
            }
        }


        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId){
        return petRepository.getOne(petId).getCustomer();
    }

}
