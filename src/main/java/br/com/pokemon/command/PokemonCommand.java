package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailMapper;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.PokemonResult;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@ApplicationScoped
public class PokemonCommand {
    private final PokemonGateway pokemonListGateway;
    private final PokemonDetailMapper pokemonDetailMapper;

    Logger LOGGER = Logger.getLogger(PokemonCommand.class.getName());

    @Inject
    public PokemonCommand(@RestClient PokemonGateway pokemonListGateway, PokemonDetailMapper pokemonDetailMapper) {
        this.pokemonListGateway = pokemonListGateway;
        this.pokemonDetailMapper = pokemonDetailMapper;
    }

    public List<PokemonDetail> execute(Integer limit) {
        LOGGER.info(String.format("[PokemonCommand:execute] Calling pokeapi.co with limit of: %s", limit));
        PokemonResultList pokemonResultList = pokemonListGateway.getPokemonNumberedList(limit);

        return pokemonResultList.getResults().stream()
                .parallel()
                .map(PokemonResult::getUrl)
                .map(PokemonCommand::getPokemonIndex)
                .map(pokemonListGateway::getPokemonById)
                .map(pokemonDetailMapper::mapperFromResultDetailsToPokemonDetail)
                .collect(Collectors.toList());
    }

    public static Integer getPokemonIndex(String url) {
        List<String> urlSplit = Arrays.asList(url.split("/"));
        return Integer.parseInt(urlSplit.get(urlSplit.size() - 1));
    }
}
