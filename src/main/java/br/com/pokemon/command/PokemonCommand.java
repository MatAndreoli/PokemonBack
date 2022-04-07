package br.com.pokemon.command;

import br.com.pokemon.command.mapper.PokemonDetailMapper;
import br.com.pokemon.domain.PokemonDetail;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.pokemon.infrastructure.gateway.PokemonGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PokemonCommand {

    private final PokemonGateway pokemonListGateway;
    private final PokemonDetailMapper pokemonDetailMapper;

    @Inject
    public PokemonCommand(@RestClient PokemonGateway pokemonListGateway, PokemonDetailMapper pokemonDetailMapper) {
        this.pokemonListGateway = pokemonListGateway;
        this.pokemonDetailMapper = pokemonDetailMapper;
    }

    public List<PokemonDetail> execute() {
        PokemonResultList pokemonResultList = this.pokemonListGateway.getPokemonList();
        List<PokemonDetail> pokemonDetails = new ArrayList<>();

        pokemonResultList.getResults().forEach(result -> {
            int a = pokemonResultList.getResults().indexOf(result);
            pokemonDetails.add(this.pokemonDetailMapper.mapperFromResultDetailsToPokemonDetail(this.pokemonListGateway.getPokemonById(a + 1)));
        });
        return pokemonDetails;
    }

}
