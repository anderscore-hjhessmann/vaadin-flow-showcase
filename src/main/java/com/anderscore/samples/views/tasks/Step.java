package com.anderscore.samples.views.tasks;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Step implements Cloneable {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    private boolean done;
}
