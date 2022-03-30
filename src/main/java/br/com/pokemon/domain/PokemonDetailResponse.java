package br.com.pokemon.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailResponse {
    public List<PokemonAbilities> abilities = new ArrayList<>();
    public Long id;
    public PokemonSprites sprites;
    public List<PokemonTypes> types;

    public List<PokemonAbilities> getAbilities() {
        return abilities;
    }

    public Long getId() {
        return id;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public List<PokemonTypes> getTypes() {
        return types;
    }
}
