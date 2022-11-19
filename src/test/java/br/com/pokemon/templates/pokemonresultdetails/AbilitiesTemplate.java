package br.com.pokemon.templates.pokemonResultDetails;

import br.com.pokemon.infrastructure.domain.PokemonAbilities;
import br.com.pokemon.infrastructure.domain.PokemonAbility;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AbilitiesTemplate implements TemplateLoader {
    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonAbilities.class).addTemplate(VALID, new Rule() {{
            add("ability", one(PokemonAbility.class, VALID));
        }});
    }

    public static PokemonAbilities gimmeAValid() {
        return Fixture.from(PokemonAbilities.class).gimme(VALID);
    }
}
