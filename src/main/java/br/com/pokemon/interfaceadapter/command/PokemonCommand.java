package br.com.pokemon.interfaceadapter.command;

import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.domain.PokemonNameUrlResponse;
import br.com.pokemon.interfaceadapter.gateway.PokemonListGateway;
import br.com.pokemon.interfaceadapter.model.PokemonDetailMapper;
import br.com.pokemon.interfaceadapter.model.PokemonDetailSimpleResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("pokemon")
public class PokemonCommand {

    @Inject
    @RestClient
    PokemonListGateway pokemonListGateway;

    PokemonDetailMapper pokemonDetailMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonNameUrlResponse pokemonList() {
        return pokemonListGateway.listPokemons();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonDetailSimpleResponse pokemonByIdTest(@PathParam("id") int id) {
        return PokemonDetailMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonListGateway.listPokemonById(id));
    }

    @GET
    @Path("list-all-details/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PokemonDetailSimpleResponse> pokemonByIdList() {
        PokemonNameUrlResponse pokemonNameUrlResponses = pokemonListGateway.listPokemons();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponse = new ArrayList<>();
        pokemonNameUrlResponses.results.forEach(result -> {
            int a = pokemonNameUrlResponses.results.indexOf(result);
            pokemonDetailSimpleResponse.add(PokemonDetailMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonListGateway.listPokemonById(a+1)));
        });
        return pokemonDetailSimpleResponse;
    }
}
