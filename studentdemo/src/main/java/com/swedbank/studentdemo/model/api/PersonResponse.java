package com.swedbank.studentdemo.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponse {

    @Schema(description = "Person fist name", example = "Karolis")
    private String firstName;

    @Schema(description = "Person last name", example = "Grigonis")
    private String lastName;

    @Schema(description = "Person email", example = "email@gmail.com")
    private String email;

    @Schema(description = "Person phone number", example = "+370645345634")
    private String phone;
}
