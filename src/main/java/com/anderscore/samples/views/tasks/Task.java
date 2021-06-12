package com.anderscore.samples.views.tasks;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Task implements Cloneable {

    private Integer id;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;

    @Future
    private LocalDate due;

    private Step plan;
    private List<Step> go;

    public Task copy() {
        return toBuilder().build();
    }
}
