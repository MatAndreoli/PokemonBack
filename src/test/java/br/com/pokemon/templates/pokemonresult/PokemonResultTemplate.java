package br.com.pokemon.templates.pokemonResult;

import br.com.pokemon.infrastructure.domain.PokemonResult;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PokemonResultTemplate implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonResult.class).addTemplate(VALID, new Rule() {{
            add("name", "charmander");
            add("url", "localhost:8080/image/1");
        }});
    }

    public static PokemonResult gimmeAValid() {
        return Fixture.from(PokemonResult.class).gimme(VALID);
    }
}
