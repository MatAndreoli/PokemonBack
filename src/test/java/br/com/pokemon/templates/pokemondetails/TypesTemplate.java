package br.com.pokemon.templates.pokemondetails;

import br.com.pokemon.infrastructure.domain.PokemonType;
import br.com.pokemon.infrastructure.domain.PokemonTypes;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TypesTemplate implements TemplateLoader {
    public static final String VALID = "valid";

    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.pokemon.templates.details");
    }

    @Override
    public void load() {
        Fixture.of(PokemonTypes.class).addTemplate(VALID, new Rule() {{
            add("type", one(PokemonType.class, VALID));
        }});
    }

    public static PokemonTypes gimmeAValid() {
        return Fixture.from(PokemonTypes.class).gimme(VALID);
    }
}
