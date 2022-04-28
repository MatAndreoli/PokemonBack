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
    PokemonDetailMapper pokemonDetailMapperMock;

    PokemonResultDetails pokemonResultDetails;
    PokemonResultList pokemonResultList;
    PokemonDetail pokemonDetail;
    List<PokemonDetail> pokemonDetails;

    @Nested
    @DisplayName("when method execute is called")
    class Execute {
        @BeforeEach
        void initData() {
            Mockito.reset(pokemonGatewayMock, pokemonDetailMapperMock);
            FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);

            pokemonCommand = new PokemonCommand(pokemonGatewayMock, pokemonDetailMapperMock);
            pokemonResultDetails = PokemonResultDetailsTemplate.gimmeAValid();
            pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
            pokemonDetail = PokemonDeatilTemplate.gimmeAValid();

            Mockito.when(pokemonGatewayMock.getPokemonNumberedList(Mockito.anyInt())).thenReturn(pokemonResultList);
            Mockito.when(pokemonGatewayMock.getPokemonById(Mockito.anyInt())).thenReturn(pokemonResultDetails);
            Mockito.when(pokemonDetailMapperMock.mapperFromResultDetailsToPokemonDetail(Mockito.any())).thenReturn(pokemonDetail);

            pokemonDetails = pokemonCommand.execute(Mockito.anyInt());
        }

        @DisplayName("then should return a List<PokemonDetail> obj")
        @Test
        void execute() {
            List<PokemonDetail> pokemonDetailResponseList = PokemonDeatilTemplate.gimmeAValidList();

            assertEquals(pokemonDetailResponseList.size(), pokemonDetails.size());
        }

        @DisplayName("then should call  method getPokemonNumberedList")
        @Test
        void verifyGatewayGetPokemonNumberedList() {
            Mockito.verify(pokemonGatewayMock, Mockito.atMostOnce()).getPokemonNumberedList(Mockito.anyInt());
        }

        @DisplayName("then should call method getPokemonById")
        @Test
        void verifyGatewayGetPokemonById() {
            Mockito.verify(pokemonGatewayMock, Mockito.atMost(2)).getPokemonById(Mockito.anyInt());
        }

        @DisplayName("then should call method mapperFromResultDetailsToPokemonDetail")
        @Test
        void verifyMapperFromResultDetailsToPokemonDetail() {
            Mockito.verify(pokemonDetailMapperMock, Mockito.atMost(2)).mapperFromResultDetailsToPokemonDetail(Mockito.any());
        }
    }

}
