package br.com.pokemon.interfaceadapter.command;

import br.com.pokemon.domain.*;
import br.com.pokemon.interfaceadapter.model.PokemonDetailMapper;
import br.com.pokemon.interfaceadapter.model.PokemonDetailSimpleResponse;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class PokemonCommandTest {

    @Mock
    PokemonDetailMapper pokemonDetailMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void pokemonList() {
        PokemonDetailResponse pokemonDetailResponse = new PokemonDetailResponse();
        PokemonAbilities pokemonAbilities = new PokemonAbilities();
        pokemonAbilities.ability = new PokemonAbility();
        PokemonTypes pokemonTypes = new PokemonTypes();
        pokemonTypes.type = new PokemonType();
        pokemonAbilities.ability.name = "fjdkafj";
        pokemonTypes.type.name = "fire";
        pokemonDetailResponse.id = 1L;
        pokemonDetailResponse.name = "bulbasaur";
        pokemonDetailResponse.sprites = new PokemonSprites();
        pokemonDetailResponse.sprites.front_default = "http://localhost:8080";
        pokemonDetailResponse.abilities.add(pokemonAbilities);
        pokemonDetailResponse.types.add(pokemonTypes);

        System.out.println(pokemonDetailResponse);
        Mockito.when(PokemonDetailMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonDetailResponse))
                .thenReturn(new PokemonDetailSimpleResponse());

    }

    @Test
    void pokemonById() {
        String body = "{\"abilities\":[],\"id\":1,\"name\":\"bulbasaur\",\"sprites\":{\"front_default\":\"https://localhots:8080\"},\"types\":[]}";
        given().get("1").then().body(is(body));
        given().get("*").then().statusCode(404);
    }

    @Test
    void pokemonByIdList() {
        String body = "[{\"abilities\":[],\"id\":1,\"name\":\"bulbasaur\",\"sprites\":{\"front_default\":\"https://localhots:8080\"},\"types\":[]}," +
                "{\"abilities\":[],\"id\":1,\"name\":\"bulbasaur\",\"sprites\":{\"front_default\":\"https://localhots:8080\"},\"types\":[]}," +
                "{\"abilities\":[],\"id\":1,\"name\":\"bulbasaur\",\"sprites\":{\"front_default\":\"https://localhots:8080\"},\"types\":[]}]";
        given().get("list-all-details").then().body(is(body));
        given().get("**/*").then().statusCode(404);
    }
}