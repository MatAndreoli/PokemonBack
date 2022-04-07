package br.com.pokemon.command.mapper;

import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.infrastructure.domain.PokemonDetails;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailResponseMapper {

    public PokemonDetailResponse mapperFromDetailResponseToPokemonDetailResponse(PokemonDetails pokemonDetailResponse) {
        List<String> typesStr = new ArrayList<>();
        List<String> abilitiesStr = new ArrayList<>();

        if (pokemonDetailResponse == null) {
            System.out.println("Algo Deu Errado!!!");
        }
        assert pokemonDetailResponse != null;

        pokemonDetailResponse.getAbilities().forEach(abilities -> abilitiesStr.add(abilities.getAbility().getName()));
        pokemonDetailResponse.getTypes().forEach(types -> typesStr.add(types.getType().getName()));

        return new PokemonDetailResponse(pokemonDetailResponse.getId(), pokemonDetailResponse.getName(),
                pokemonDetailResponse.getSprites().getFront_default(), abilitiesStr, typesStr);
    }
}
