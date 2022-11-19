package br.com.pokemon.templates.pokemonResultDetails;

import br.com.pokemon.infrastructure.domain.PokemonType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TypeTemplate implements TemplateLoader {
    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonType.class).addTemplate(VALID, new Rule() {{
            add("name", "fire");
        }});
    }

    public static PokemonType gimmeAValid() {
        return Fixture.from(PokemonType.class).gimme(VALID);
    }
}
