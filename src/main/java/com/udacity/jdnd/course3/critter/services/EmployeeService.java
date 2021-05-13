package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(long employeeId){
        return employeeRepository.getOne(employeeId);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate localDate){
        return employeeRepository.findByDaysAvailability(localDate.getDayOfWeek()).stream().filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
    }


}
