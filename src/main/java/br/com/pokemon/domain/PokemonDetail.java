package br.com.pokemon.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetail {
    public Long id;
    public String name;
    public String front_default;
    public List<String> abilities = new ArrayList<>();
    public List<String> types = new ArrayList<>();

    public PokemonDetail() {
    }

    public PokemonDetail(Long id, String name, String front_default, List<String> abilities, List<String> types) {
        this.id = id;
        this.name = name;
        this.front_default = front_default;
        this.abilities = abilities;
        this.types = types;
    }

    @Override
    public String toString() {
        return "PokemonDetail{" +
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
}
