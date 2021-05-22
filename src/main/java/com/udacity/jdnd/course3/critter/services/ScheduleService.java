package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;


    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds){
        List<Employee> employees = new ArrayList<>();
        for (Long employeeId : employeeIds){
            Employee employee =employeeRepository.getOne(employeeId);
            employees.add(employee);
        }

        List<Pet> pets = new ArrayList<>();

        for (Long petId : petIds){
            Pet pet = petRepository.getOne(petId);
            pets.add(pet);
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId){
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.getAllSchedulesByPet(pet);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllSchedulesByEmployee(employee);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId){
        List<Pet> pets = petRepository.getAllByCustomerId(customerId);
        return scheduleRepository.getAllByPetsIn(pets);
    }





}
