package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailMapper;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.domain.exceptions.PokemonApiException;
import br.com.pokemon.infrastructure.domain.PokemonResult;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;

@ApplicationScoped
public class PokemonCommand {
    private static final String LOG_PREFIX = "[PokemonResource:execute]";
    private final PokemonGateway pokemonListGateway;
    private final PokemonDetailMapper pokemonDetailMapper;

    static final Logger LOGGER = Logger.getLogger(PokemonCommand.class.getName());

    @Inject
    public PokemonCommand(@RestClient PokemonGateway pokemonListGateway, PokemonDetailMapper pokemonDetailMapper) {
        this.pokemonListGateway = pokemonListGateway;
        this.pokemonDetailMapper = pokemonDetailMapper;
    }

    public List<PokemonDetail> execute(Integer limit) {
        try {
            String logText = format("%s Calling pokeapi.co with limit of: %s", LOG_PREFIX, limit);
            LOGGER.info(logText);
            PokemonResultList pokemonResultList = pokemonListGateway.getPokemonNumberedList(limit);

            return pokemonResultList.getResults().stream()
                    .parallel()
                    .map(PokemonResult::getUrl)
                    .map(PokemonCommand::getPokemonIndex)
                    .map(pokemonListGateway::getPokemonById)
                    .map(pokemonDetailMapper::mapperFromResultDetailsToPokemonDetail)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            String logText = format("%s Error getting pokemons list %s", LOG_PREFIX, e);
            LOGGER.severe(logText);
            throw new PokemonApiException("Error getting pokemons list", e);
        }
    }

    public static Integer getPokemonIndex(String url) {
        List<String> urlSplit = Arrays.asList(url.split("/"));
        return Integer.parseInt(urlSplit.get(urlSplit.size() - 1));
    }
}
