package com.svalero.medicalapp.exception;

public class PatientNotFoundException extends Exception {

    public PatientNotFoundException() {
        super("This patient doest not exist.");
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}
