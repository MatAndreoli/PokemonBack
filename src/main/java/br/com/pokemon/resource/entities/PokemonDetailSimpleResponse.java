package br.com.pokemon.resource.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailSimpleResponse {
    public Long id;
    public String name;
    public String front_default;
    public List<String> abilities = new ArrayList<>();
    public List<String> types = new ArrayList<>();

    public PokemonDetailSimpleResponse() {
    }

    public PokemonDetailSimpleResponse(Long id, String name, String front_default, List<String> abilities, List<String> types) {
        this.id = id;
        this.name = name;
        this.front_default = front_default;
        this.abilities = abilities;
        this.types = types;
    }

    @Override
    public String toString() {
        return "PokemonDetailSimpleResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", front_default='" + front_default + '\'' +
                ", abilities=" + abilities +
                ", types=" + types +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFront_default() {
        return front_default;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
