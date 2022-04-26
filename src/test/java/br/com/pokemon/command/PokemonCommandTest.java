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
import org.junit.jupiter.api.*;
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

    PokemonResultDetails pokemonResultDetails;
    PokemonResultList pokemonResultList;
    PokemonDetail pokemonDetail;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonCommand = new PokemonCommand(pokemonGatewayMock, pokemonDetailResponseMapper);
        pokemonResultDetails = PokemonResultDetailsTemplate.gimmeAValid();
        pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
        pokemonDetail = PokemonDeatilTemplate.gimmeAValid();
    }

    @Nested
    @DisplayName("when method execute is called")
    class Execute {
        @DisplayName("then should return a List<PokemonDetail> obj")
        @Test
        void execute() {
            List<PokemonDetail> pokemonDetailResponseList = PokemonDeatilTemplate.gimmeAValidList();

            Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
            Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonResultDetails);

            List<PokemonDetail> pokemonDetailResponsesResult = pokemonCommand.execute(Mockito.anyInt());

            assertEquals(pokemonDetailResponseList.size(), pokemonDetailResponsesResult.size());
        }

        @DisplayName("then should call  method getPokemonNumberedList")
        @Test
        void verifyGatewayGetPokemonNumberedList() {
            Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
            Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonResultDetails);

            pokemonCommand.execute(Mockito.anyInt());

            Mockito.verify(pokemonGatewayMock, Mockito.atLeast(1)).getPokemonNumberedList(Mockito.anyInt());
        }

        @DisplayName("then should call method getPokemonById")
        @Test
        void verifyGatewayGetPokemonById() {
            Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
            Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonResultDetails);
            Mockito.when(pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(Mockito.any())).thenReturn(pokemonDetail);

            pokemonCommand.execute(Mockito.anyInt());

            Mockito.verify(pokemonGatewayMock, Mockito.atLeast(2)).getPokemonById(Mockito.anyInt());
        }

        @DisplayName("then should call method mapperFromResultDetailsToPokemonDetail")
        @Test
        void verifyMapperFromResultDetailsToPokemonDetail() {
            Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
            Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonResultDetails);
            Mockito.when(pokemonDetailResponseMapper.mapperFromResultDetailsToPokemonDetail(Mockito.any())).thenReturn(pokemonDetail);

            pokemonCommand.execute(Mockito.anyInt());

            Mockito.verify(pokemonDetailResponseMapper, Mockito.atLeast(2)).mapperFromResultDetailsToPokemonDetail(Mockito.any());
        }
    }

}
