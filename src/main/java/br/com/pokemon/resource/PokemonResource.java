package br.com.pokemon.resource;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.pokemon.resource.mapper.PokemonDetailSimpleResponseMapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("pokemons")
public class PokemonResource {

    private final PokemonCommand pokemonCommand;

    private final PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper;

    @Inject
    public PokemonResource(PokemonCommand pokemonCommand, PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper) {
        this.pokemonCommand = pokemonCommand;
        this.pokemonDetailSimpleResponseMapper = pokemonDetailSimpleResponseMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PokemonDetailSimpleResponse> pokemonList() {
        return this.pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(this.pokemonCommand.execute(50));
    }
}
