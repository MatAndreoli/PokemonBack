package br.com.pokemon.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonNameUrlResponse {
    public List<PokemonNameUrl> results = new ArrayList<>();

    public List<PokemonNameUrl> getResults() {
        return results;
    }
}
