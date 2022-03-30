/*
package br.com.pokemon.interfaceadapter.command;

import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.domain.PokemonNameUrl;
import br.com.pokemon.domain.PokemonNameUrlResponse;
import br.com.pokemon.domain.PokemonSprites;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Mock
@ApplicationScoped
public class MockPokemonExternalService extends PokemonCommand{

//    @Override
//    public PokemonNameUrlResponse pokemonList() {
//        PokemonNameUrl pokemonNameUrl = new PokemonNameUrl();
//        PokemonNameUrlResponse pokemonNameUrlResponse = new PokemonNameUrlResponse();
//        pokemonNameUrlResponse.results.add(pokemonNameUrl);
//        pokemonNameUrlResponse.results.add(pokemonNameUrl);
//        pokemonNameUrlResponse.results.add(pokemonNameUrl);
//        return pokemonNameUrlResponse;
//    }

//    @Override
//    public PokemonDetailResponse pokemonById(@PathParam("id") int id) {
//        return this.initResponse();
//    }

//    @Override
//    public List<PokemonDetailResponse> pokemonByIdList() {
//        PokemonNameUrlResponse pokemonNameUrlResponses = pokemonListGateway.listPokemons();
//        List<PokemonDetailResponse> pokemonDetailResponseList = new ArrayList<>();
//        pokemonDetailResponseList.add(this.initResponse());
//        pokemonDetailResponseList.add(this.initResponse());
//        pokemonDetailResponseList.add(this.initResponse());
//        return pokemonDetailResponseList;
//    }

    public PokemonDetailResponse initResponse() {
        PokemonDetailResponse pokemonDetailResponse = new PokemonDetailResponse();
        pokemonDetailResponse.id = 1L;
        pokemonDetailResponse.name = "bulbasaur";
        pokemonDetailResponse.sprites = new PokemonSprites();
        pokemonDetailResponse.sprites.front_default = "https://localhots:8080";
        return pokemonDetailResponse;
    }
}
*/
