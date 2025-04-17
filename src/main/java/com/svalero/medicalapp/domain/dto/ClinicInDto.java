package com.svalero.medicalapp.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ClinicInDto {

    @NotNull(message = "El campo name es obligatorio")
    private String name;
    @NotNull(message = "El campo address es obligatorio")
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private boolean open;


}
