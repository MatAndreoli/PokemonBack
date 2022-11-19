package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailMapper;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.domain.exceptions.PokemonApiException;
import br.com.pokemon.infrastructure.domain.PokemonResultDetails;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import br.com.pokemon.templates.TemplatesPath;
import br.com.pokemon.templates.pokemonDetail.PokemonDetailTemplate;
import br.com.pokemon.templates.pokemonResult.PokemonResultListTemplate;
import br.com.pokemon.templates.pokemonResultDetails.PokemonResultDetailsTemplate;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonCommandTest {
    @Mock
    PokemonGateway pokemonGatewayMock;
    @Mock
    PokemonDetailMapper pokemonDetailMapperMock;
    @InjectMocks
    PokemonCommand pokemonCommand;

    PokemonResultDetails pokemonResultDetails;
    PokemonResultList pokemonResultList;
    PokemonDetail pokemonDetail;
    List<PokemonDetail> pokemonDetails;
    @Captor
    ArgumentCaptor<Integer> pokemonIdCaptor;

    @BeforeAll
    static void init() {
        FixtureFactoryLoader.loadTemplates(TemplatesPath.TEMPLATES_PATH);
    }

    @Nested
    @DisplayName("Given PokemonCommand is started")
    class GivenPokemonCommand {
        @Nested
        @DisplayName("When execute is called")
        class Execute {
            @Nested
            @DisplayName("And succeeds")
            class AndSucceeds {
                @BeforeEach
                void mockAndAct() {
                    reset(pokemonGatewayMock, pokemonDetailMapperMock);
                    pokemonResultList = PokemonResultListTemplate.gimmeAValid2();
                    pokemonResultDetails = PokemonResultDetailsTemplate.gimmeAValid();
                    pokemonDetail = PokemonDetailTemplate.gimmeAValid();

                    when(pokemonGatewayMock.getPokemonNumberedList(anyInt())).thenReturn(pokemonResultList);
                    when(pokemonGatewayMock.getPokemonById(anyInt())).thenReturn(pokemonResultDetails);
                    when(pokemonDetailMapperMock.mapperFromResultDetailsToPokemonDetail(any())).thenReturn(pokemonDetail);

                    pokemonDetails = pokemonCommand.execute(anyInt());
                    verify(pokemonGatewayMock, atLeast(1)).getPokemonById(pokemonIdCaptor.capture());
                }

                @Test
                @DisplayName("Then return a PokemonDetails list")
                void execute() {
                    assertThat(pokemonDetails).hasSize(2);
                }

                @Test
                @DisplayName("Then call getPokemonNumberedList")
                void verifyGatewayGetPokemonNumberedList() {
                    verify(pokemonGatewayMock, atMostOnce()).getPokemonNumberedList(anyInt());
                }

                @Test
                @DisplayName("Then call getPokemonById with pokemon id")
                void verifyGatewayGetPokemonById() {
                    assertThat(pokemonIdCaptor.getValue())
                            .isEqualTo(1);
                }

                @Test
                @DisplayName("Then call mapperFromResultDetailsToPokemonDetail")
                void verifyMapperFromResultDetailsToPokemonDetail() {
                    verify(pokemonDetailMapperMock, atMost(2)).mapperFromResultDetailsToPokemonDetail(any());
                }
            }

            @Nested
            @DisplayName("And fails")
            class AndFails {
                @BeforeEach
                void mockAndAct() {
                    reset(pokemonGatewayMock, pokemonDetailMapperMock);
                    when(pokemonGatewayMock.getPokemonNumberedList(anyInt())).thenThrow(new RuntimeException());
                }

                @Test
                @DisplayName("PokemonApiException")
                void thenThrowPokemonApiException() {
                    assertThrows(PokemonApiException.class, () -> pokemonDetails = pokemonCommand.execute(anyInt()));
                }
            }
        }
    }
}
