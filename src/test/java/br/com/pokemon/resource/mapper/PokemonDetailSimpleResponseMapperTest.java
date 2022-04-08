package br.com.pokemon.resource.mapper;

import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemondetailsimpleresponse.PokemonDetailSimpleResponseTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PokemonDetailSimpleResponseMapperTest {

    PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonDetailSimpleResponseMapper = new PokemonDetailSimpleResponseMapper();
    }

    @Test
    void mapperFromDetailResponseToDetailSimpleResponse() {
        List<PokemonDetail> pokemonDetailsTemp = PokemonDeatilTemplate.gimmeAValidList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponsesTemp = PokemonDetailSimpleResponseTemplate.gimmeAValidList();
        List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonDetailsTemp);

        assertEquals(pokemonDetailSimpleResponses.size(), pokemonDetailSimpleResponsesTemp.size());
    }
}
