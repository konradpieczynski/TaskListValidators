package com.kodilla.tasklist;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;
@Value
public class TaskDto {
    @NotNull
    @DateMin("2023-01-01")
    LocalDate when;
    @NotNull
    String title;
    @Range
    int priority;
}