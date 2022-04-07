package br.com.pokemon.templates.pokemondetails;

import br.com.pokemon.infrastructure.domain.PokemonSprites;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class SpritesTemplate implements TemplateLoader {

    public static final String VALID = "valid";

    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.pokemon.templates.details");
    }

    @Override
    public void load() {
        Fixture.of(PokemonSprites.class).addTemplate(VALID, new Rule() {{
            add("front_default", "http:some/image");
        }});
    }

    public static PokemonSprites gimmeAValid() {
        return Fixture.from(PokemonSprites.class).gimme(VALID);
    }
}
