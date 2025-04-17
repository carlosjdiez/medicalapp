package com.svalero.medicalapp.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class SpecialtyOutDto {

    private long id;
    private String name;
    private String description;
    private boolean requiresReferral;
    private int averageDuration;
    private boolean active;


}


