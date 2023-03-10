package com.skypro.connecting_libraries_6.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode

public class Ingredient {
    @NotBlank(message = "Имя обязательно")
    private String name;
    @Positive
    private Integer count;
    private String measureUnit;
}