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

@ApplicationScoped
public class PokemonCommand {

    private final PokemonGateway pokemonListGateway;
    private final PokemonDetailMapper pokemonDetailMapper;

    @Inject
    public PokemonCommand(@RestClient PokemonGateway pokemonListGateway, PokemonDetailMapper pokemonDetailMapper) {
        this.pokemonListGateway = pokemonListGateway;
        this.pokemonDetailMapper = pokemonDetailMapper;
    }

    public List<PokemonDetail> execute(Integer limit) {
        PokemonResultList pokemonResultList = this.pokemonListGateway.getPokemonNumberedList(limit);

        return pokemonResultList.getResults().stream()
                .parallel()
                .map(PokemonResult::getUrl)
                .map(PokemonCommand::getPokemonIndex)
                .map(this.pokemonListGateway::getPokemonById)
                .map(this.pokemonDetailMapper::mapperFromResultDetailsToPokemonDetail)
                .collect(Collectors.toList());
    }

    public static Integer getPokemonIndex(String url) {
        List<String> urlSplit = Arrays.asList(url.split("/"));
        return Integer.parseInt(urlSplit.get(urlSplit.size() - 1));
    }

}
