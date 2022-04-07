package br.com.pokemon.templates.pokemondetails;

import br.com.pokemon.infrastructure.domain.*;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PokemonDetailsTemplate implements TemplateLoader {

    private static final String VALID = "valid";

    public static void loadTemplates(){
        FixtureFactoryLoader.loadTemplates("br.com.pokemon.templates");
    }

    @Override
    public void load() {
        Fixture.of(PokemonDetails.class).addTemplate(VALID, new Rule(){{
            add("id", 2L);
            add("name", "bulbasaur");
            add("sprites", one(PokemonSprites.class, VALID));
            add("abilities", has(2).of(PokemonAbilities.class, VALID));
            add("types", has(2).of(PokemonTypes.class, VALID));
        }});
    }

    public static PokemonDetails gimmeAValid() {
        return Fixture.from(PokemonDetails.class).gimme(VALID);
    }
}
