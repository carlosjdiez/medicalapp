package com.svalero.medicalapp.exception;

public class SpecialtyNotFoundException extends Exception {

    public SpecialtyNotFoundException() {
        super("This specialty doest not exist.");
    }

    public SpecialtyNotFoundException(String message) {
        super(message);
    }
}
