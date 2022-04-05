package br.com.pokemon.infrastructure.command;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class PokemonCommandTest {

    @Mock
    PokemonCommand pokemonCommand;

    List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = new ArrayList<>();
    PokemonDetailSimpleResponse pokemonDetailSimpleResponse = new PokemonDetailSimpleResponse();

    @BeforeEach
    public void before() {
        init();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void pokemonList() {
        Mockito.when(pokemonCommand.execute())
                .thenReturn(pokemonDetailSimpleResponses);
        List<PokemonDetailSimpleResponse> test = pokemonCommand.execute();
        assertEquals(test, pokemonDetailSimpleResponses);
    }

    private void init() {
        pokemonDetailSimpleResponse.name = "bulbasaur";
        pokemonDetailSimpleResponse.id = 1L;
        pokemonDetailSimpleResponse.abilities.add("fire");
        pokemonDetailSimpleResponse.abilities.add("water");
        pokemonDetailSimpleResponse.types.add("grass");
        pokemonDetailSimpleResponse.types.add("poison");
        pokemonDetailSimpleResponse.front_default = "urlImage";

        pokemonDetailSimpleResponses.add(pokemonDetailSimpleResponse);
        pokemonDetailSimpleResponses.add(pokemonDetailSimpleResponse);
    }

}
