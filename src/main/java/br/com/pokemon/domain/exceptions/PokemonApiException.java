package br.com.pokemon.domain.exceptions;

public class PokemonApiException extends RuntimeException {
    public PokemonApiException(String msg, Throwable e) {
        super(msg, e);
    }
}
