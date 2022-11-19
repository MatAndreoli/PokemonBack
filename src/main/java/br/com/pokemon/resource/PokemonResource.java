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
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

@Path("pokemons")
public class PokemonResource {
    private static final String LOG_PREFIX = "[PokemonResource:pokemonList]";
    private final PokemonCommand pokemonCommand;

    static final Logger LOGGER = Logger.getLogger(PokemonResource.class.getName());

    @Inject
    public PokemonResource(PokemonCommand pokemonCommand) {
        this.pokemonCommand = pokemonCommand;
    }

    @GET
    @Path("/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pokemonList(@PathParam("limit") Integer limit) {
        try {
            String logText = format("%s Getting pokemon list with limit of: %s", LOG_PREFIX, limit);
            LOGGER.info(logText);
            List<PokemonDetail> pokemonDetails = pokemonCommand.execute(limit);
            return Response.status(Response.Status.OK).entity(pokemonDetails).build();
        } catch (final RuntimeException e) {
            String logText = format("%s Error getting pokemons list %s", LOG_PREFIX, e);
            LOGGER.severe(logText);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
