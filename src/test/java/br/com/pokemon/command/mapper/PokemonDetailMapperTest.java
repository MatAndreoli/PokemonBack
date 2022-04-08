package br.com.pokemon.command.mapper;

import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.PokemonResultDetails;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemonresultdetails.PokemonResultDetailsTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PokemonDetailMapperTest {

    PokemonDetailMapper pokemonDetailMapper;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonDetailMapper = new PokemonDetailMapper();
    }

    @DisplayName("Should return a PokemonDetail when called with a valid param")
    @Test
    void mapperFromResultDetailsToPokemonDetail() {
        PokemonResultDetails pokemonResultDetails = PokemonResultDetailsTemplate.gimmeAValid();
        PokemonDetail pokemonDetailTemp = PokemonDeatilTemplate.gimmeAValid();
        PokemonDetail pokemonDetail = pokemonDetailMapper.mapperFromResultDetailsToPokemonDetail(pokemonResultDetails);

        assertEquals(pokemonDetail.toString(), pokemonDetailTemp.toString());
    }
}
