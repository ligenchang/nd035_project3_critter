package com.udacity.jdnd.course3.critter.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue
    private Long id;


    @Nationalized // should use @Nationalized instead of @Type=nstring
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public void addskill(EmployeeSkill employeeSkill){
        skills.add(employeeSkill);
    }

    public void addAvailableDay(DayOfWeek dayOfWeek){
        daysAvailable.add(dayOfWeek);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
