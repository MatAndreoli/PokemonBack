package br.com.pokemon.templates.pokemonResultDetails;

import br.com.pokemon.infrastructure.domain.PokemonSprites;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class SpritesTemplate implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonSprites.class).addTemplate(VALID, new Rule() {{
            add("frontDefault", "localhost:image/url");
        }});
    }

    public static PokemonSprites gimmeAValid() {
        return Fixture.from(PokemonSprites.class).gimme(VALID);
    }
}
