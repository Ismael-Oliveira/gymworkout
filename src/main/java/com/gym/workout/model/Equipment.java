package com.gym.workout.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private Integer code;
    private StatusEquipment statusEquipment;

    public Equipment(){}

    public Equipment(String name, Integer code) {
        this.name = name;
        this.date = LocalDate.now();
        this.code = code;
        this.statusEquipment = StatusEquipment.AVAILABLE;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public StatusEquipment getStatusEquipment() {
        return statusEquipment;
    }

    public void setStatusEquipment(StatusEquipment statusEquipment) {
        this.statusEquipment = statusEquipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipment equipment = (Equipment) o;
        return Objects.equals(name, equipment.name) && Objects.equals(code, equipment.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    @Override
    public String toString() {
        return "Equipment{" + "name='" + name + '\'' + ", date=" + date + ", code=" + code + ", statusEquipment="
                + statusEquipment + '}';
    }
}
