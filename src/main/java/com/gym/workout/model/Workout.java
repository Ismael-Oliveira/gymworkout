package com.gym.workout.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameExercise;
    private Integer loop;
    private Integer weight;
    private Integer series;
    private Boolean selectedExercise = true;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Workout() {}

    public Workout(String nameExercise, Integer loop, Integer weight, Integer series, Category category) {
        this.nameExercise = nameExercise;
        this.loop = loop;
        this.weight = weight;
        this.series = series;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameExercise() {
        return nameExercise;
    }

    public void setNameExercise(String nameExercise) {
        this.nameExercise = nameExercise;
    }

    public Integer getLoop() {
        return loop;
    }

    public void setLoop(Integer loop) {
        this.loop = loop;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Boolean getSelectedExercise() {
        return selectedExercise;
    }

    public void setSelectedExercise(Boolean selectedExercise) {
        this.selectedExercise = selectedExercise;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workout that = (Workout) o;
        return Objects.equals(id, that.id) && Objects.equals(loop, that.loop) && Objects.equals(weight, that.weight) && Objects
                .equals(series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loop, weight, series);
    }

    @Override
    public String toString() {
        return "Workout{" + "loop=" + loop + ", weight=" + weight + ", Series=" + series + '}';
    }
}
