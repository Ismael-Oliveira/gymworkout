package com.gym.workout.model;

public enum StatusEquipment {

    AVAILABLE("Disponível"),
    UNAVAILABLE("Indisponível"),
    MAINTENANCE("Manutenção");

    private String description;

    StatusEquipment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
