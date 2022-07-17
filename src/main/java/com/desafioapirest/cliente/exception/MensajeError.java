package com.desafioapirest.cliente.exception;

public class MensajeError {
    private String error;

    public MensajeError(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
