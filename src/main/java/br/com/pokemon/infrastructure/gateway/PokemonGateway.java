package br.com.pokemon.infrastructure.gateway;

import br.com.pokemon.infrastructure.domain.PokemonResultDetails;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://pokeapi.co/api/v2/")
@Produces(MediaType.APPLICATION_JSON)
public interface PokemonGateway {

    @GET
    @Path("pokemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    PokemonResultDetails getPokemonById(@PathParam("id") Integer id);

    @GET
    @Path("pokemon")
    @Produces(MediaType.APPLICATION_JSON)
    PokemonResultList getPokemonNumberedList(@QueryParam("limit") Integer limit);
}
