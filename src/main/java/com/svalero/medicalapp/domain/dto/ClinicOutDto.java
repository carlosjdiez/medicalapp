package com.svalero.medicalapp.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class ClinicOutDto {

    private long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private boolean open;


}
