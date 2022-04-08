package br.com.pokemon.resource.mapper;

import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemondetailsimpleresponse.PokemonDetailSimpleResponseTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PokemonDetailSimpleResponseMapperTest {

    PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonDetailSimpleResponseMapper = new PokemonDetailSimpleResponseMapper();
    }

    @DisplayName("when method mapperFromDetailResponseToDetailSimpleResponse is called should return a List<PokemonDetailSimpleResponse> obj")
    @Test
    void mapperFromDetailResponseToDetailSimpleResponse() {
        List<PokemonDetail> pokemonDetailsTemp = PokemonDeatilTemplate.gimmeAValidList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponsesTemp = PokemonDetailSimpleResponseTemplate.gimmeAValidList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonDetailsTemp);

        assertEquals(pokemonDetailSimpleResponses.size(), pokemonDetailSimpleResponsesTemp.size());
    }

    @DisplayName("when method mapperFromDetailResponseToDetailSimpleResponse is called with an empty list should throw a NullPointerException")
    @Test
    void mapperFromDetailResponseToDetailSimpleResponse1() {
        List<PokemonDetail> pokemonDetailsTemp = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonDetailsTemp));
    }
}
