package com.svalero.medicalapp.exception;

public class DoctorNotFoundException extends Exception {

    public DoctorNotFoundException() {
        super("This doctor doest not exist.");
    }

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
