package br.com.pokemon.templates.pokemonResultDetails;

import br.com.pokemon.infrastructure.domain.PokemonAbility;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AbilityTemplate implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonAbility.class).addTemplate(VALID, new Rule() {{
            add("name", "fire ball");
        }});
    }

    public static PokemonAbility gimmeAValid() {
        return Fixture.from(PokemonAbility.class).gimme(VALID);
    }
}
