package br.com.pokemon.templates.pokemondetailreponse;

import br.com.pokemon.domain.PokemonDetailResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class PokemonDeatilsResponseTemplate implements TemplateLoader {
    public static final String VALID = "valid";

    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.pokemon.templates.pokemondetailreponse");
    }

    @Override
    public void load() {
        List<String> abilities = new ArrayList<>();
        List<String> types = new ArrayList<>();
        abilities.add("fire ball");
        abilities.add("poison atack");
        types.add("fire");
        types.add("water");
        Fixture.of(PokemonDetailResponse.class).addTemplate(VALID, new Rule() {{
            add("id", 1L);
            add("name", "charmander");
            add("front_default", "localhost:image/url");
            add("abilities", abilities);
            add("types", types);
        }});
    }

    public static PokemonDetailResponse gimmeAValid() {
        return Fixture.from(PokemonDetailResponse.class).gimme(VALID);
    }
}
