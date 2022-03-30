package br.com.pokemon.interfaceadapter.model;

import br.com.pokemon.domain.PokemonDetailResponse;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailMapper {

    public static PokemonDetailSimpleResponse mapperFromDetailResponseToDetailSimpleResponse(PokemonDetailResponse pokemonDetailResponse) {
        List<String> typesStr = new ArrayList<>();
        List<String> abilitiesStr = new ArrayList<>();

        if (pokemonDetailResponse == null) {
            System.out.println("Algo Deu Errado!!!");
        }
        assert pokemonDetailResponse != null;

        pokemonDetailResponse.abilities.forEach(abilities -> abilitiesStr.add(abilities.ability.name));
        pokemonDetailResponse.types.forEach(types -> typesStr.add(types.type.name));

        return new PokemonDetailSimpleResponse(pokemonDetailResponse.id, pokemonDetailResponse.getName(),
                pokemonDetailResponse.getSprites().front_default, abilitiesStr, typesStr);
    }
}
