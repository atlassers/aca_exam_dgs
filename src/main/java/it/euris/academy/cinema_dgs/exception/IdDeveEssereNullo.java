package it.euris.academy.cinema_dgs.exception;

public class IdDeveEssereNullo extends RuntimeException{
    public IdDeveEssereNullo() {
        super("L entità inserita ha un ID. ID deve essere nullo per far funzionare questo metodo");
    }
}
