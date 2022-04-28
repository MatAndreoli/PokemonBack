package br.com.pokemon.resource;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.pokemon.resource.mapper.PokemonDetailSimpleResponseMapper;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemondetailsimpleresponse.PokemonDetailSimpleResponseTemplate;
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
class PokemonResourceTest {

    PokemonResource pokemonResource;

    @Mock
    PokemonCommand pokemonCommandMock;

    @Mock
    PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapperMock;

    List<PokemonDetail> pokemonDetails;
    List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses;

    @Nested
    @DisplayName("when method pokemonList is called")
    class PokemonResourceTests {
        @BeforeEach
        void initData() {
            Mockito.reset(pokemonCommandMock, pokemonDetailSimpleResponseMapperMock);
            FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);

            pokemonResource = new PokemonResource(pokemonCommandMock, pokemonDetailSimpleResponseMapperMock);
            pokemonDetails = PokemonDeatilTemplate.gimmeAValidList();
            pokemonDetailSimpleResponses = PokemonDetailSimpleResponseTemplate.gimmeAValidList();

            Mockito.when(pokemonCommandMock.execute(Mockito.anyInt())).thenReturn(pokemonDetails);
            Mockito.when(pokemonDetailSimpleResponseMapperMock.mapperFromDetailResponseToDetailSimpleResponse(Mockito.anyList()))
                    .thenReturn(pokemonDetailSimpleResponses);

            pokemonResource.pokemonList(Mockito.anyInt());
        }

        @DisplayName("then should return a List<PokemonDetailSimpleResponse> obj")
        @Test
        void pokemonList() {
            List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses1 = pokemonResource.pokemonList(Mockito.anyInt());

            assertEquals(pokemonDetailSimpleResponses.toString(), pokemonDetailSimpleResponses1.toString());
        }

        @DisplayName("then should call method execute")
        @Test
        void verifyCommandExecute() {
            Mockito.verify(pokemonCommandMock, Mockito.atMostOnce()).execute(Mockito.anyInt());
        }

        @DisplayName("then should call method mapperFromDetailResponseToDetailSimpleResponse")
        @Test
        void verifyMapperFromDetailResponseToDetailSimpleResponse() {
            Mockito.verify(pokemonDetailSimpleResponseMapperMock, Mockito.atMostOnce())
                    .mapperFromDetailResponseToDetailSimpleResponse(Mockito.anyList());
        }
    }
}
