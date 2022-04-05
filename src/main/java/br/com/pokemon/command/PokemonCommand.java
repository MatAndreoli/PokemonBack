package br.com.pokemon.command;

import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import br.com.pokemon.infrastructure.mapper.PokemonDetailMapper;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PokemonCommand {

    private final PokemonGateway pokemonListGateway;

    @Inject
    public PokemonCommand(@RestClient PokemonGateway pokemonListGateway) {
        this.pokemonListGateway = pokemonListGateway;
    }

    public List<PokemonDetailSimpleResponse> execute() {
        PokemonResultList pokemonNameUrlResponses = pokemonListGateway.getPokemonList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponse = new ArrayList<>();

        pokemonNameUrlResponses.results.forEach(result -> {
            int a = pokemonNameUrlResponses.results.indexOf(result);
            pokemonDetailSimpleResponse.add(PokemonDetailMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonListGateway.getPokemonById(a + 1)));
        });
        return pokemonDetailSimpleResponse;
    }

}
