package it.euris.academy.cinema_dgs.exception;

public class IdNonDeveEssereNullo extends RuntimeException{

    public IdNonDeveEssereNullo() {
        super("L entità inserita non ha un ID. ID non deve essere nullo per far funzionare questo metodo.");
    }
}
