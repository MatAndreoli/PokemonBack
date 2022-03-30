package br.com.pokemon.interfaceadapter.resource;

import br.com.pokemon.interfaceadapter.command.PokemonCommand;
import br.com.pokemon.interfaceadapter.model.PokemonDetailSimpleResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("pokemons")
public class PokemonResource {

    PokemonCommand pokemonCommand;

    public PokemonResource (PokemonCommand pokemonCommand) {
        this.pokemonCommand = pokemonCommand;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PokemonDetailSimpleResponse> pokemonList() {
        return pokemonCommand.execute();
    }
}
