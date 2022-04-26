package br.com.pokemon.command.mapper;

import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.PokemonResultDetails;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
import br.com.pokemon.templates.pokemonresultdetails.PokemonResultDetailsTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PokemonDetailMapperTest {

    PokemonDetailMapper pokemonDetailMapper;

    @BeforeEach
    void initData() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
        pokemonDetailMapper = new PokemonDetailMapper();
    }

    @Nested
    @DisplayName("when method mapperFromResultDetailsToPokemonDetail is called")
    class mapperFromResultDetailsToPokemonDetail {
        @DisplayName("then should return a PokemonDetail obj")
        @Test
        void assetEqualsMapperFromResultDetailsToPokemonDetail() {
            PokemonResultDetails pokemonResultDetails = PokemonResultDetailsTemplate.gimmeAValid();
            PokemonDetail pokemonDetailTemp = PokemonDeatilTemplate.gimmeAValid();
            PokemonDetail pokemonDetail = pokemonDetailMapper.mapperFromResultDetailsToPokemonDetail(pokemonResultDetails);

            assertEquals(pokemonDetail.toString(), pokemonDetailTemp.toString());
        }

        @Nested
        @DisplayName("and its param is empty")
        class mapperFromResultDetailsToPokemonDetailInvalid {
            @DisplayName("then should throw a NullPointerException")
            @Test
            void assertThrowsMapperFromResultDetailsToPokemonDetail() {
                assertThrows(NullPointerException.class, () -> pokemonDetailMapper.mapperFromResultDetailsToPokemonDetail(null));
            }
        }
    }

}
