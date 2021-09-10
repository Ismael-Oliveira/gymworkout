package com.gym.workout.controller.form;

import com.gym.workout.model.Performance;

public class PerformanceForm {

    private Double height;
    private Double weight;
    private Integer age;
    private Integer sex;
    private String observation;

    public PerformanceForm () {}

    public PerformanceForm(Performance performance) {
        this.height = performance.getHeight();
        this.weight = performance.getWeight();
        this.age = performance.getAgeOld();
        this.sex = performance.getSex();
        this.observation = performance.getObservation();
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSex() {
        return sex;
    }

    public String getObservation() {
        return observation;
    }

    public Double BMICalculator() {
        return null;
    }

    public Double bodyFat() {
        return null;
    }

    public Performance converter() {
        return new Performance(this.getHeight(), this.getWeight(), this.age, this.sex, this.observation);
    }
}
