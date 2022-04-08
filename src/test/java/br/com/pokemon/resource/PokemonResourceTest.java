package br.com.pokemon.resource;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.pokemon.resource.mapper.PokemonDetailSimpleResponseMapper;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemondetailsimpleresponse.PokemonDetailSimpleResponseTemplate;
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
class PokemonResourceTest {

    PokemonResource pokemonResource;

    @Mock
    PokemonCommand pokemonCommandMock;

    @Mock
    PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonResource = new PokemonResource(pokemonCommandMock, pokemonDetailSimpleResponseMapper);
    }

    @DisplayName("when method pokemonResource.pokemonList() is called should return a List<PokemonDetailSimpleResponse> obj")
    @Test
    void pokemonList() {
        List<PokemonDetail> pokemonDetailResponses = PokemonDeatilTemplate.gimmeAValidList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = PokemonDetailSimpleResponseTemplate.gimmeAValidList();

        Mockito.when(pokemonCommandMock.execute()).thenReturn(pokemonDetailResponses);
        Mockito.when(pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(Mockito.<PokemonDetail>anyList())).thenReturn(pokemonDetailSimpleResponses);

        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses1 = pokemonResource.pokemonList();

        assertEquals(pokemonDetailSimpleResponses.toString(), pokemonDetailSimpleResponses1.toString());

    }

    @DisplayName("when method pokemonResource.pokemonList() is called should call pokemonCommandMock.execute() at least once")
    @Test
    void verifyCommandExecute() {
        List<PokemonDetail> pokemonDetailResponses = PokemonDeatilTemplate.gimmeAValidList();

        Mockito.when(pokemonCommandMock.execute()).thenReturn(pokemonDetailResponses);

        pokemonResource.pokemonList();

        Mockito.verify(pokemonCommandMock, Mockito.atLeast(1)).execute();
    }

    @DisplayName("when method pokemonResource.pokemonList() is called " +
            "should call pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(List<PokemonDetail> obj) at least once")
    @Test
    void verifyMapperFromDetailResponseToDetailSimpleResponse() {
        List<PokemonDetail> pokemonDetailResponses = PokemonDeatilTemplate.gimmeAValidList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = PokemonDetailSimpleResponseTemplate.gimmeAValidList();

        Mockito.when(pokemonCommandMock.execute()).thenReturn(pokemonDetailResponses);
        Mockito.when(pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(Mockito.<PokemonDetail>anyList()))
                .thenReturn(pokemonDetailSimpleResponses);

        pokemonResource.pokemonList();

        Mockito.verify(pokemonDetailSimpleResponseMapper, Mockito.atLeast(1)).mapperFromDetailResponseToDetailSimpleResponse(Mockito.anyList());
    }
}
