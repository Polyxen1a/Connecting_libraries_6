package com.skypro.connecting_libraries_6.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString

public class Recipe {
    @NotBlank
    private String name;
    @Positive
    private Integer cookingTime;
    @NotEmpty
    private List<Ingredient> ingredients;
    @NotEmpty
    private List<String> steps;
}