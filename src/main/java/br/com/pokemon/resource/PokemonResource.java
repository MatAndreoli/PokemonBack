package br.com.pokemon.resource;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.domain.PokemonDetail;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import java.util.List;

@Path("pokemons")
public class PokemonResource {
    private final PokemonCommand pokemonCommand;

    Logger LOGGER = Logger.getLogger(PokemonResource.class.getName());

    @Inject
    public PokemonResource(PokemonCommand pokemonCommand) {
        this.pokemonCommand = pokemonCommand;
    }

    @GET
    @Path("/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pokemonList(@PathParam("limit") Integer limit) {
        try {
            LOGGER.info(String.format("[PokemonResource:pokemonList] Getting pokemon list with limit of: %s", limit));
            List<PokemonDetail> pokemonDetails = pokemonCommand.execute(limit);
            return Response.ok(pokemonDetails).build();
        } catch (final Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        try {
            LOGGER.info(String.format("[PokemonResource:test] Test endpoint"));
            return Response.ok("Testing").build();
        } catch (final Exception e) {
            return Response.serverError().build();
        }
    }
}
