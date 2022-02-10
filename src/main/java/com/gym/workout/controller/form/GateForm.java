package com.gym.workout.controller.form;

import javax.validation.constraints.NotNull;

public class GateForm {

    @NotNull
    private Integer registerClient;

    public Integer getRegisterClient() {
        return registerClient;
    }

}
