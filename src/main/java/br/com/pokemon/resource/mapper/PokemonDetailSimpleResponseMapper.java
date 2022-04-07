package br.com.pokemon.resource.mapper;

import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailSimpleResponseMapper {
    public static List<PokemonDetailSimpleResponse> mapperFromDetailResponseToDetailSimpleResponse(List<PokemonDetailResponse> finalResponses) {
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = new ArrayList<>();

        if (finalResponses.isEmpty()) {
            System.out.println("Algo Deu Errado!!!");
        }

        finalResponses.forEach(finalResponse -> {
            List<String> abilitiesStr = new ArrayList<>(finalResponse.getAbilities());
            List<String> typesStr = new ArrayList<>(finalResponse.getTypes());
            pokemonDetailSimpleResponses.add(new PokemonDetailSimpleResponse(finalResponse.getId(), finalResponse.getName(),
                    finalResponse.getFront_default(), abilitiesStr, typesStr));
        });

        return pokemonDetailSimpleResponses;
    }
}
