package br.com.pokemon.domain;

import javax.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;
import java.util.List;

public class PokemonDetail {
    private Long id;
    private String name;
    @JsonbProperty("front_default")
    private String frontDefault;
    private List<String> abilities = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    public PokemonDetail() {
    }

    public PokemonDetail(Long id, String name, String front_default, List<String> abilities, List<String> types) {
        this.id = id;
        this.name = name;
        this.frontDefault = front_default;
        this.abilities = abilities;
        this.types = types;
    }

    @Override
    public String toString() {
        return "PokemonDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", front_default='" + frontDefault + '\'' +
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

    public String getFrontDefault() {
        return frontDefault;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public List<String> getTypes() {
        return types;
    }
}
