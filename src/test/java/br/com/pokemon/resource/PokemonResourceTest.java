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
    PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper;

    List<PokemonDetail> pokemonDetailResponses;
    List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonResource = new PokemonResource(pokemonCommandMock, pokemonDetailSimpleResponseMapper);
        pokemonDetailResponses = PokemonDeatilTemplate.gimmeAValidList();
        pokemonDetailSimpleResponses = PokemonDetailSimpleResponseTemplate.gimmeAValidList();
    }

    @Nested
    @DisplayName("when method pokemonList is called")
    class PokemonResourceTests {
        @DisplayName("then should return a List<PokemonDetailSimpleResponse> obj")
        @Test
        void pokemonList() {
            Mockito.when(pokemonCommandMock.execute(Mockito.anyInt())).thenReturn(pokemonDetailResponses);
            Mockito.when(pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(Mockito.anyList())).thenReturn(pokemonDetailSimpleResponses);

            List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses1 = pokemonResource.pokemonList(Mockito.anyInt());

            assertEquals(pokemonDetailSimpleResponses.toString(), pokemonDetailSimpleResponses1.toString());
        }

        @DisplayName("then should call method execute")
        @Test
        void verifyCommandExecute() {
            Mockito.when(pokemonCommandMock.execute(Mockito.anyInt())).thenReturn(pokemonDetailResponses);

            pokemonResource.pokemonList(Mockito.anyInt());

            Mockito.verify(pokemonCommandMock, Mockito.atLeast(1)).execute(Mockito.anyInt());
        }

        @DisplayName("then should call method mapperFromDetailResponseToDetailSimpleResponse")
        @Test
        void verifyMapperFromDetailResponseToDetailSimpleResponse() {
            Mockito.when(pokemonCommandMock.execute(Mockito.anyInt())).thenReturn(pokemonDetailResponses);
            Mockito.when(pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(Mockito.anyList()))
                    .thenReturn(pokemonDetailSimpleResponses);

            pokemonResource.pokemonList(Mockito.anyInt());

            Mockito.verify(pokemonDetailSimpleResponseMapper, Mockito.atLeast(1)).mapperFromDetailResponseToDetailSimpleResponse(Mockito.anyList());
        }
    }
}
