package br.com.pokemon.interfaceadapter.command;

import br.com.pokemon.domain.PokemonNameUrl;
import br.com.pokemon.domain.PokemonNameUrlResponse;
import io.quarkus.test.Mock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(PokemonCommand.class)
class PokemonCommandTest {
    PokemonNameUrl pokemonNameUrl = new PokemonNameUrl();
    PokemonNameUrlResponse pokemonNameUrlResponse = new PokemonNameUrlResponse();

    @BeforeEach
    public void before() {
        pokemonNameUrlResponse.results.add(pokemonNameUrl);
        pokemonNameUrlResponse.results.add(pokemonNameUrl);
        pokemonNameUrlResponse.results.add(pokemonNameUrl);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void pokemonList() {
        given()
                .get()
                .then().statusCode(200);
        String body =  "{\"results\":[{},{},{}]}";
        given().get().then().body(is(body));
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