package br.com.pokemon.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;

public class PokemonSprites {

    @JsonbProperty("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
