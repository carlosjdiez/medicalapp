package com.svalero.medicalapp.exception;

public class AppointmentNotFoundException extends Exception {

    public AppointmentNotFoundException() {
        super("This appointment doest not exist.");
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
