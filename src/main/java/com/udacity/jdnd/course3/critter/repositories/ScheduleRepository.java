package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where :pet member of s.pets")
    public List<Schedule> getAllSchedulesByPet(Pet pet);

    @Query("select s from Schedule s where :employee member of s.employees")
    public List<Schedule> getAllSchedulesByEmployee(Employee employee);
    public List<Schedule> getAllByPetsIn(List<Pet> pets);

}
