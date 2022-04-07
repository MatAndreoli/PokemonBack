package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailMapper;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.*;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import br.com.pokemon.templates.pokemondetail.PokemonDetailTemplate;
import br.com.pokemon.templates.pokemondetailresponse.PokemonDeatilsResponseTemplate;
import br.com.pokemon.templates.pokemonresult.PokemonResultListTemplate;
import br.com.pokemon.templates.pokemonresult.PokemonResultTemplate;
import br.com.pokemon.templates.pokemondetails.PokemonResultDetailsTemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PokemonCommandTest {

    PokemonCommand pokemonCommand;

    @Mock
    PokemonGateway pokemonGatewayMock;

    @Mock
    PokemonDetailMapper pokemonDetailResponseMapper;

    @BeforeEach
    void initData() {
        PokemonResultTemplate.loadTemplates();
        PokemonResultDetailsTemplate.loadTemplates();
        PokemonDeatilsResponseTemplate.loadTemplates();
        PokemonDetailTemplate.loadTemplates();
        pokemonCommand = new PokemonCommand(pokemonGatewayMock, pokemonDetailResponseMapper);
    }

    @DisplayName("when method execute is called should return a List<PokemonDetail> obj")
    @Test
    void execute() {
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        List<PokemonDetail> pokemonDetailResponseList = PokemonDeatilsResponseTemplate.gimmeAValidList();
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();

        Mockito.when(pokemonGatewayMock.getPokemonList()).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);

        List<PokemonDetail> pokemonDetailResponsesResult = pokemonCommand.execute();

        assertEquals(pokemonDetailResponseList.size(), pokemonDetailResponsesResult.size());
    }

    @DisplayName("when method execute is called should call pokemonGatewayMock.getPokemonList() at least once")
    @Test
    void verifyGatewayGetList() {
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();

        Mockito.when(pokemonGatewayMock.getPokemonList()).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);

        pokemonCommand.execute();

        Mockito.verify(pokemonGatewayMock, Mockito.atLeast(1)).getPokemonList();
    }

    @DisplayName("when method execute is called should call pokemonGatewayMock.getPokemonById(int i) at least twice")
    @Test
    void verifyGatewayGetPokemonById() {
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonDetail pokemonDetail = PokemonDetailTemplate.gimmeAValid();

        Mockito.when(pokemonGatewayMock.getPokemonList()).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);
        Mockito.when(pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(Mockito.<PokemonResultDetails>any())).thenReturn(pokemonDetail);

        pokemonCommand.execute();

        Mockito.verify(pokemonGatewayMock, Mockito.atLeast(2)).getPokemonById(Mockito.anyInt());
    }

    @DisplayName("when method execute is called should call " +
            "pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(PokemonResultDetails obj) at least twice")
    @Test
    void verifymapperFromDetailResponseToPokemonDetailResponse() {
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
        PokemonDetail pokemonDetail = PokemonDetailTemplate.gimmeAValid();

        Mockito.when(pokemonGatewayMock.getPokemonList()).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);
        Mockito.when(pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(Mockito.<PokemonResultDetails>any())).thenReturn(pokemonDetail);

        pokemonCommand.execute();

        Mockito.verify(pokemonDetailResponseMapper, Mockito.atLeast(2)).mapperFromResultDetailsToPokemonDetail(Mockito.<PokemonResultDetails>any());
    }

}
