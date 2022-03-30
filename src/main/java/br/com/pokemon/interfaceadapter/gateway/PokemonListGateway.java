package br.com.pokemon.interfaceadapter.gateway;

import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.domain.PokemonNameUrlResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://pokeapi.co/api/v2/")
@Produces(MediaType.APPLICATION_JSON)
public interface PokemonListGateway {

    @GET
    @Path("pokemon")
    @Produces(MediaType.APPLICATION_JSON)
    PokemonNameUrlResponse listPokemons();

    @GET
    @Path("pokemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    PokemonDetailResponse listPokemonById(@PathParam("id") Long id);
}
