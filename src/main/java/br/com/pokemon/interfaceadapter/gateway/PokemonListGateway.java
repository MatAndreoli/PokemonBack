package br.com.pokemon.interfaceadapter.gateway;

import br.com.pokemon.domain.PokemonNameUrlList;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://pokeapi.co/api/v2/")
@Produces(MediaType.APPLICATION_JSON)
public interface PokemonListGateway {

    @GET
    @Path("pokemon")
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonNameUrlList listPokemons();
}
