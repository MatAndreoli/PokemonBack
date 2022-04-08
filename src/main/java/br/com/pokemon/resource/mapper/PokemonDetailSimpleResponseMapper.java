package br.com.pokemon.resource.mapper;

import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PokemonDetailSimpleResponseMapper {

    public List<PokemonDetailSimpleResponse> mapperFromDetailResponseToDetailSimpleResponse(List<PokemonDetail> pokemonDetails) {
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = new ArrayList<>();

        if (pokemonDetails.isEmpty()) {
            throw new NullPointerException("PokemonResultDetails cannot be null");
        }

        pokemonDetails.forEach(pokemonDetailResponse -> {
            List<String> abilitiesStr = new ArrayList<>(pokemonDetailResponse.getAbilities());
            List<String> typesStr = new ArrayList<>(pokemonDetailResponse.getTypes());
            pokemonDetailSimpleResponses.add(new PokemonDetailSimpleResponse(pokemonDetailResponse.getId(), pokemonDetailResponse.getName(),
                    pokemonDetailResponse.getFront_default(), abilitiesStr, typesStr));
        });

        return pokemonDetailSimpleResponses;
    }
}
