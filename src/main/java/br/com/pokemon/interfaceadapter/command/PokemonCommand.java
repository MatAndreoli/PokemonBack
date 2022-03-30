package br.com.pokemon.interfaceadapter.command;

import br.com.pokemon.domain.PokemonNameUrlResponse;
import br.com.pokemon.interfaceadapter.gateway.PokemonListGateway;
import br.com.pokemon.interfaceadapter.model.PokemonDetailMapper;
import br.com.pokemon.interfaceadapter.model.PokemonDetailSimpleResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PokemonCommand {

    private final PokemonListGateway pokemonListGateway;

    @Inject
    public PokemonCommand(@RestClient PokemonListGateway pokemonListGateway) {
        this.pokemonListGateway = pokemonListGateway;
    }

    public List<PokemonDetailSimpleResponse> execute() {
        PokemonNameUrlResponse pokemonNameUrlResponses = pokemonListGateway.listPokemons();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponse = new ArrayList<>();

        pokemonNameUrlResponses.results.forEach(result -> {
            int a = pokemonNameUrlResponses.results.indexOf(result);
            pokemonDetailSimpleResponse.add(PokemonDetailMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonListGateway.listPokemonById(a+1)));
        });
        return pokemonDetailSimpleResponse;
    }

}
