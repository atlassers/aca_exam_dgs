package it.euris.academy.cinema_dgs.exception;

public class SalaAlCompleto extends RuntimeException{

    public SalaAlCompleto() {
        super("La sala è già piena");
    }
}
