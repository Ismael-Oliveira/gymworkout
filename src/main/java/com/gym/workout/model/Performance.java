package com.gym.workout.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double height;
    private Double weight;
    private Integer ageOld;
    private Integer sex;
    private LocalDate dateEvaluation;
    private String observation;

    @ManyToMany(mappedBy = "performances")
    private List<Client> clients = new ArrayList<>();

    public Performance() {}

    public Performance(Double height, Double weight, Integer ageOld, Integer sex, String observation) {
        this.height = height;
        this.weight = weight;
        this.ageOld = ageOld;
        this.sex = sex;
        this.dateEvaluation = LocalDate.now();
        this.observation = observation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getAgeOld() {
        return ageOld;
    }

    public void setAgeOld(Integer ageOld) {
        this.ageOld = ageOld;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public LocalDate getDateEvaluation() {
        return dateEvaluation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Performance that = (Performance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Performance{" + "height=" + height + ", weight=" + weight + ", age=" + ageOld + ", sex=" + sex + '}';
    }
}
