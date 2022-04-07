package br.com.pokemon.templates.pokemondetailsimpleresponse;

import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailSimpleResponseTemplate implements TemplateLoader {
    private static final String VALID = "valid";

    public static void loadTemplates(){
        FixtureFactoryLoader.loadTemplates("br.com.pokemon.templates.pokemondetailsimpleresponse");
    }

    @Override
    public void load() {
        List<String> abilities = new ArrayList<>();
        List<String> types = new ArrayList<>();
        abilities.add("fire ball");
        abilities.add("fire ball");
        types.add("fire");
        types.add("fire");
        Fixture.of(PokemonDetailSimpleResponse.class).addTemplate(VALID, new Rule(){{
            add("id", 1L);
            add("name", "charmander");
            add("front_default", "localhost:image/url");
            add("abilities", abilities);
            add("types", types);
        }});
    }

    public static PokemonDetailSimpleResponse gimmeAValid() {
        return Fixture.from(PokemonDetailSimpleResponse.class).gimme(VALID);
    }

    public static List<PokemonDetailSimpleResponse> gimmeAValidList() {
        return Fixture.from(PokemonDetailSimpleResponse.class).gimme(2, VALID);
    }
}
