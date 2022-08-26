//package br.com.pokemon.resource.mapper;
//
//import br.com.pokemon.domain.PokemonDetail;
//import br.com.pokemon.templates.TemplatesPath;
//import br.com.pokemon.templates.pokemondetail.PokemonDeatilTemplate;
//import br.com.pokemon.templates.pokemondetailsimpleresponse.PokemonDetailSimpleResponseTemplate;
//import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class PokemonDetailSimpleResponseMapperTest {
//
//    PokemonDetailSimpleResponseMapper pokemonDetailSimpleResponseMapper;
//
//    @BeforeEach
//    void initData() {
//        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
//        pokemonDetailSimpleResponseMapper = new PokemonDetailSimpleResponseMapper();
//    }
//
//    @Nested
//    @DisplayName("when method mapperFromDetailResponseToDetailSimpleResponse is called")
//    class Mapper {
//        @DisplayName("then should return a List<PokemonDetailSimpleResponse> obj")
//        @Test
//        void mapperFromDetailResponseToDetailSimpleResponse() {
//            List<PokemonDetail> pokemonDetails = PokemonDeatilTemplate.gimmeAValidList();
//            List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponsesTemp = PokemonDetailSimpleResponseTemplate.gimmeAValidList();
//            List<PokemonDetailSimpleResponse> pokemonDetailSimpleResponses = pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonDetails);
//
//            assertEquals(pokemonDetailSimpleResponses.size(), pokemonDetailSimpleResponsesTemp.size());
//        }
//
//        @Nested
//        @DisplayName("and its param is empty")
//        class Mapper1 {
//            @DisplayName("then should throw a NullPointerException")
//            @Test
//            void mapperFromDetailResponseToDetailSimpleResponse1() {
//                List<PokemonDetail> pokemonDetailsEmpty = new ArrayList<>();
//
//                assertThrows(NullPointerException.class, () -> pokemonDetailSimpleResponseMapper.mapperFromDetailResponseToDetailSimpleResponse(pokemonDetailsEmpty));
//            }
//        }
//    }
//
//}
