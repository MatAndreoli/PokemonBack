package br.com.pokemon.command.mapper;

import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.PokemonResultDetails;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PokemonDetailMapper {

    public PokemonDetail mapperFromResultDetailsToPokemonDetail(PokemonResultDetails pokemonDetailResponse) {
        List<String> typesStr = new ArrayList<>();
        List<String> abilitiesStr = new ArrayList<>();

        if (pokemonDetailResponse == null) {
            throw new NullPointerException("PokemonResultDetails cannot be null");
        }

        pokemonDetailResponse.getAbilities().forEach(abilities -> abilitiesStr.add(abilities.getAbility().getName()));
        pokemonDetailResponse.getTypes().forEach(types -> typesStr.add(types.getType().getName()));

        return new PokemonDetail(pokemonDetailResponse.getId(), pokemonDetailResponse.getName(),
                pokemonDetailResponse.getSprites().getFront_default(), abilitiesStr, typesStr);
    }
}
