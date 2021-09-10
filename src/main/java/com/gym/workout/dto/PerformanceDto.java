package com.gym.workout.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.gym.workout.model.Performance;

public class PerformanceDto {

    private Long id;
    private Double height;
    private Double weight;
    private Integer age;
    private Integer sex;
    private LocalDate dateEvaluation;
    private String observation;

    public PerformanceDto(Performance performance) {
        this.id = performance.getId();
        this.height = performance.getHeight();
        this.weight = performance.getWeight();
        this.age = performance.getAgeOld();
        this.sex = performance.getSex();
        this.dateEvaluation = performance.getDateEvaluation();
        this.observation = performance.getObservation();
    }

    public Long getId() {
        return id;
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

    public LocalDate getDateEvaluation() {
        return dateEvaluation;
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

    public static Page<PerformanceDto> converter(Page<Performance> performances) {
        Page<PerformanceDto> performanceDtos = performances.map(PerformanceDto::new);
        return  performanceDtos;
    }
}
