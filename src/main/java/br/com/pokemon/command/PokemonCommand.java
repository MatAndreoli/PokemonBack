package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailResponseMapper;
import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PokemonCommand {

    private final PokemonGateway pokemonListGateway;
    PokemonDetailResponseMapper pokemonDetailResponseMapper = new PokemonDetailResponseMapper();

    @Inject
    public PokemonCommand(@RestClient PokemonGateway pokemonListGateway) {
        this.pokemonListGateway = pokemonListGateway;
    }

    public List<PokemonDetailResponse> execute() {
        PokemonResultList pokemonNameUrlResponses = this.pokemonListGateway.getPokemonList();
        List<PokemonDetailResponse> finalResponse = new ArrayList<>();

        pokemonNameUrlResponses.getResults().forEach(result -> {
            int a = pokemonNameUrlResponses.getResults().indexOf(result);
            finalResponse.add(this.pokemonDetailResponseMapper.mapperFromDetailResponseToPokemonDetailResponse(this.pokemonListGateway.getPokemonById(a + 1)));
        });
        return finalResponse;
    }

}
