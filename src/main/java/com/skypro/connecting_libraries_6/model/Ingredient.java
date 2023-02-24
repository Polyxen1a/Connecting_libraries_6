package com.skypro.connecting_libraries_6.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString

public class Ingredient {
    @NotBlank(message = "Имя обязательно")
    private String name;
    @Positive
    private Integer count;
    private String measureUnit;
}