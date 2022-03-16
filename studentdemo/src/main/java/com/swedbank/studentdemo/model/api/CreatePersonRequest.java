package com.swedbank.studentdemo.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreatePersonRequest {

    @Schema(description = "Person fist name", example = "Karolis")
    @NonNull
    @Size(min = 3, max = 20)
    private String firstName;

    @Schema(description = "Person middle name", example = "Julius")
    private String middleName;

    @Schema(description = "Person last name", example = "Grigonis")
    @NonNull
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String lastName;

    @Schema(description = "Person email", example = "email@gmail.com")
    private String email;

    @Schema(description = "Person phone number", example = "+370645345634")
    private String phone;
}
