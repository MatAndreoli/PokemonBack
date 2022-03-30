package br.com.pokemon.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailResponse {

    public List<PokemonAbilities> abilities = new ArrayList<>();
    public Long id;
    public String name;
    public PokemonSprites sprites;

    public List<PokemonTypes> types= new ArrayList<>();

    @Override
    public String toString() {
        return "PokemonDetailResponse{" +
                "abilities=" + abilities +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sprites=" + sprites +
                ", types=" + types +
                '}';
    }

    public List<PokemonAbilities> getAbilities() {
        return abilities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public List<PokemonTypes> getTypes() {
        return types;
    }
}
