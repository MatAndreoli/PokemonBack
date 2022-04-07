package br.com.pokemon.templates.pokemondetail;

import br.com.pokemon.domain.PokemonDetail;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailTemplate implements TemplateLoader {
    private static final String VALID = "valid";

    public static void loadTemplates(){
        FixtureFactoryLoader.loadTemplates("br.com.pokemon.templates.pokemondetail");
    }

    @Override
    public void load() {
        Fixture.of(PokemonDetail.class).addTemplate(VALID, new Rule(){{
            List<String> abilities = new ArrayList<>();
            List<String> types = new ArrayList<>();
            abilities.add("fire ball");
            abilities.add("fire ball");
            types.add("fire");
            types.add("fire");
            add("id", 1L);
            add("name", "charmander");
            add("front_default", "localhost:image/url");
            add("abilities", abilities);
            add("types", types);
        }});
    }

    public static PokemonDetail gimmeAValid() {
        return Fixture.from(PokemonDetail.class).gimme(VALID);
    }
}
