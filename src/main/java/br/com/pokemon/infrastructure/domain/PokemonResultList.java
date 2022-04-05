package br.com.pokemon.infrastructure.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonResultList {
    public List<PokemonResult> results = new ArrayList<>();

    public List<PokemonResult> getResults() {
        return results;
    }
}
