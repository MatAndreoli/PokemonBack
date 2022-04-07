package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailResponseMapper;
import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.pokemon.infrastructure.domain.*;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import br.com.pokemon.templates.pokemondetailreponse.PokemonDeatilsResponseTemplate;
import br.com.pokemon.templates.pokemonresult.PokemonResultListTemplate;
import br.com.pokemon.templates.pokemonresult.PokemonResultTemplate;
import br.com.pokemon.templates.pokemondetails.PokemonDetailsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PokemonCommandTest {

    PokemonCommand pokemonCommand;

    @Mock
    PokemonGateway pokemonGatewayMock;

    @Mock
    PokemonDetailResponseMapper pokemonDetailResponseMapperMock = new PokemonDetailResponseMapper();

    PokemonDetailResponse pokemonDetailResponse = new PokemonDetailResponse();
    PokemonResultList pokemonResultList = new PokemonResultList();
    PokemonDetails pokemonDetails = new PokemonDetails();

    @BeforeEach
    void initData() {
        PokemonResultTemplate.loadTemplates();
        PokemonDetailsTemplate.loadTemplates();
        PokemonDeatilsResponseTemplate.loadTemplates();
        pokemonCommand = new PokemonCommand(pokemonGatewayMock);
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("PokemonGateway.getPokemonList() should return a PokemonResultList object")
    @Test
    void gatewayShouldReturnPokemonResultList() {
        pokemonResultList.setResults(PokemonResultListTemplate.gimmeAValid2().getResults());

        Mockito.when(pokemonGatewayMock.getPokemonList()).thenReturn(pokemonResultList);

        assertEquals(pokemonResultList, pokemonGatewayMock.getPokemonList());
    }

    @DisplayName("PokemonDetailResponseMapper.mapperFromDetailResponseToPokemonDetailResponse(PokemonDetails obj) should return a PokemonDetailResponse object")
    @Test
    void mapperShouldReturnPokemonDetailResponse() {
        pokemonDetails = PokemonDetailsTemplate.gimmeAValid();
        pokemonDetailResponse = PokemonDeatilsResponseTemplate.gimmeAValid();

        Mockito.when(pokemonDetailResponseMapperMock.mapperFromDetailResponseToPokemonDetailResponse(pokemonDetails)).thenReturn(pokemonDetailResponse);

        assertEquals(pokemonDetailResponse, pokemonDetailResponseMapperMock.mapperFromDetailResponseToPokemonDetailResponse(pokemonDetails));
    }
}
