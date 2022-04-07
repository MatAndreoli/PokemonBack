package br.com.pokemon.infrastructure.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonResultDetails {

    private List<PokemonAbilities> abilities = new ArrayList<>();
    private Long id;
    private String name;
    private PokemonSprites sprites;
    private List<PokemonTypes> types = new ArrayList<>();

    @Override
    public String toString() {
        return "PokemonResultDetails{" +
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

    public void setAbilities(List<PokemonAbilities> abilities) {
        this.abilities = abilities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public void setTypes(List<PokemonTypes> types) {
        this.types = types;
    }
}
