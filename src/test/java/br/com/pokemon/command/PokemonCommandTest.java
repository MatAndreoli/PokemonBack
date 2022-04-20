package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailMapper;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.PokemonResultDetails;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemonresult.PokemonResultListTemplate;
import br.com.pokemon.templates.pokemonresultdetails.PokemonResultDetailsTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonCommand = new PokemonCommand(pokemonGatewayMock, pokemonDetailResponseMapper);
    }

    @DisplayName("when method execute is called should return a List<PokemonDetail> obj")
    @Test
    void execute() {
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        List<PokemonDetail> pokemonDetailResponseList = PokemonDeatilTemplate.gimmeAValidList();
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();

        Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);

        List<PokemonDetail> pokemonDetailResponsesResult = pokemonCommand.execute(Mockito.anyInt());

        assertEquals(pokemonDetailResponseList.size(), pokemonDetailResponsesResult.size());
    }

    @DisplayName("when method execute is called should call pokemonGatewayMock.getPokemonList() at least once")
    @Test
    void verifyGatewayGetList() {
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();

        Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);

        pokemonCommand.execute(Mockito.anyInt());

        Mockito.verify(pokemonGatewayMock, Mockito.atLeast(1)).getPokemonNumberedList(Mockito.anyInt());
    }

    @DisplayName("when method execute is called should call pokemonGatewayMock.getPokemonById(int i) at least twice")
    @Test
    void verifyGatewayGetPokemonById() {
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonDetail pokemonDetail = PokemonDeatilTemplate.gimmeAValid();

        Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);
        Mockito.when(pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(Mockito.<PokemonResultDetails>any())).thenReturn(pokemonDetail);

        pokemonCommand.execute(Mockito.anyInt());

        Mockito.verify(pokemonGatewayMock, Mockito.atLeast(2)).getPokemonById(Mockito.anyInt());
    }

    @DisplayName("when method execute is called should call " +
            "pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(PokemonResultDetails obj) at least twice")
    @Test
    void verifyMapperFromDetailResponseToPokemonDetailResponse() {
        PokemonResultDetails pokemonDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonResultList pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
        PokemonDetail pokemonDetail = PokemonDeatilTemplate.gimmeAValid();

        Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
        Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonDetails);
        Mockito.when(pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(Mockito.<PokemonResultDetails>any())).thenReturn(pokemonDetail);

        pokemonCommand.execute(Mockito.anyInt());

        Mockito.verify(pokemonDetailResponseMapper, Mockito.atLeast(2)).mapperFromResultDetailsToPokemonDetail(Mockito.<PokemonResultDetails>any());
    }

}
