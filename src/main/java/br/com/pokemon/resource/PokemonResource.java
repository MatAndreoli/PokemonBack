package br.com.pokemon.resource;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.pokemon.resource.mapper.PokemonDetailSimpleResponseMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("pokemons")
public class PokemonResource {

    private final PokemonCommand pokemonCommand;

    public PokemonResource(PokemonCommand pokemonCommand) {
        this.pokemonCommand = pokemonCommand;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PokemonDetailSimpleResponse> pokemonList() {
        return PokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(this.pokemonCommand.execute());
    }
}
