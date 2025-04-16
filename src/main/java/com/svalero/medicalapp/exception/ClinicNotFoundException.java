package com.svalero.medicalapp.exception;

public class ClinicNotFoundException extends Exception {

    public ClinicNotFoundException() {
        super("This clinic doest not exist.");
    }

    public ClinicNotFoundException(String message) {
        super(message);
    }
}
