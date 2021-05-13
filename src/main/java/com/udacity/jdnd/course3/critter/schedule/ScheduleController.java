package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setSkills(scheduleDTO.getSkills());
        return getScheduleDTO(scheduleService.saveSchedule(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds()));
    }

    private ScheduleDTO getScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setSkills(schedule.getSkills());
        List<Long> employeeIds = new ArrayList<>();

        for (Employee employee : schedule.getEmployees()){
            employeeIds.add(employee.getId());
        }
        List<Long> petIds = new ArrayList<>();

        for (Pet pet: schedule.getPets()){
            petIds.add(pet.getId());
        }
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream().map(this::getScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleForPet(petId).stream().map(this::getScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId).stream().map(this::getScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream().map(this::getScheduleDTO).collect(Collectors.toList());
    }
}
