package br.com.pokemon.templates.pokemonresultdetails;

import br.com.pokemon.infrastructure.domain.*;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PokemonResultDetailsTemplate implements TemplateLoader {

    private static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonResultDetails.class).addTemplate(VALID, new Rule(){{
            add("id", 1L);
            add("name", "charmander");
            add("sprites", one(PokemonSprites.class, VALID));
            add("abilities", has(2).of(PokemonAbilities.class, VALID));
            add("types", has(2).of(PokemonTypes.class, VALID));
        }});
    }

    public static PokemonResultDetails gimmeAValid() {
        return Fixture.from(PokemonResultDetails.class).gimme(VALID);
    }
}
