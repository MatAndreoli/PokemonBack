package br.com.pokemon.interfaceadapter.command;

import br.com.pokemon.domain.PokemonNameUrlList;
import br.com.pokemon.interfaceadapter.gateway.PokemonListGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("pokemon")
public class PokemonCommand {

    @Inject
    @RestClient
    PokemonListGateway pokemonListGateway;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonNameUrlList pokemonList() {
        return pokemonListGateway.listPokemons();
    }
}
