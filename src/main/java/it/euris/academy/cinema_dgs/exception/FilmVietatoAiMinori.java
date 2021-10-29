package it.euris.academy.cinema_dgs.exception;

public class FilmVietatoAiMinori extends RuntimeException{

    public FilmVietatoAiMinori() {
        super("Film vietato ai minori");
    }
}
