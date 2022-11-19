package br.com.pokemon.resource;

import br.com.pokemon.command.PokemonCommand;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemonDetail.PokemonDetailTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonResourceTest {
    @Mock
    PokemonCommand pokemonCommandMock;
    @InjectMocks
    PokemonResource pokemonResource;
    List<PokemonDetail> pokemonDetails, pokemonDetailsResult;
    Response result;

    @BeforeAll
    static void init() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
    }

    @Nested
    @DisplayName("Given PokemonResource is started")
    class GivenPokemonResource {
        @Nested
        @DisplayName("When pokemonList is called")
        class PokemonList {
            @BeforeEach
            void mock() {
                reset(pokemonCommandMock);
            }

            @Nested
            @DisplayName("And succeeds")
            class AndSucceeds {
                @BeforeEach
                void mockAndAct() {
                    pokemonDetails = PokemonDetailTemplate.gimmeAValidList();
                    when(pokemonCommandMock.execute(anyInt())).thenReturn(pokemonDetails);
                    pokemonDetailsResult = (List<PokemonDetail>) pokemonResource.pokemonList(anyInt()).getEntity();
                }

                @DisplayName("Then return a pokemon list")
                @Test
                void thenReturnAPokemonList() {
                    assertThat(pokemonDetailsResult)
                            .hasSize(2)
                            .extracting("name", "abilities")
                            .containsExactly(
                                    tuple("charmander", Arrays.asList("fire ball", "fire ball")),
                                    tuple("charmander", Arrays.asList("fire ball", "fire ball"))
                            );
                }

                @DisplayName("Then return status 200")
                @Test
                void thenReturnStatus200() {
                    assertThat(result)
                            .extracting("status")
                            .isEqualTo(500);
                }

                @DisplayName("Then call pokemonCommand.execute")
                @Test
                void thenCallPokemonCommandExecute() {
                    verify(pokemonCommandMock, atMostOnce()).execute(anyInt());
                }
            }

            @Nested
            @DisplayName("And fails")
            class AndFails {
                @BeforeEach
                void mockAndAct() {
                    when(pokemonCommandMock.execute(anyInt())).thenThrow(new RuntimeException("An error occurred"));
                    result = pokemonResource.pokemonList(anyInt());
                }

                @DisplayName("Then return status 500")
                @Test
                void thenReturnStatus500() {
                    assertThat(result)
                            .extracting("status")
                            .isEqualTo(500);
                }
            }
        }
    }
}
