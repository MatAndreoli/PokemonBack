package br.com.pokemon.templates.pokemonresult;

import br.com.pokemon.infrastructure.domain.PokemonResult;
import br.com.pokemon.infrastructure.domain.PokemonResultList;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PokemonResultListTemplate implements TemplateLoader {

    public static final String VALID_RESULT = "valid";
    public static final String VALID3 = "valid3";
    public static final String VALID2 = "valid2";

    @Override
    public void load() {
        Fixture.of(PokemonResultList.class).addTemplate(VALID3, new Rule() {{
            add("results", has(3).of(PokemonResult.class, VALID_RESULT));
        }});

        Fixture.of(PokemonResultList.class).addTemplate(VALID2, new Rule() {{
            add("results", has(2).of(PokemonResult.class, VALID_RESULT));
        }});
    }

    public static PokemonResultList gimmeAValid3() {
        return Fixture.from(PokemonResultList.class).gimme(VALID3);
    }

    public static PokemonResultList gimmeAValid2() {
        return Fixture.from(PokemonResultList.class).gimme(VALID2);
    }

}
