package br.com.pokemon.templates.pokemondetailsimpleresponse;

import br.com.pokemon.resource.entities.PokemonDetailSimpleResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokemonDetailSimpleResponseTemplate implements TemplateLoader {
    private static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonDetailSimpleResponse.class).addTemplate(VALID, new Rule(){{
            add("id", 1L);
            add("name", "charmander");
            add("front_default", "localhost:image/url");
            add("abilities", getListTo("abilities"));
            add("types", getListTo("types"));
        }});
    }

    public List<String> getListTo(String kind) {
        List<String> list = new ArrayList<>();

        if (Objects.equals(kind, "abilities")) {
            list.add("fire ball");
            list.add("fire ball");
        }
        else if (Objects.equals(kind, "types")) {
            list.add("fire");
            list.add("fire");
        }
        else {
            throw new IllegalArgumentException("The value is invalid, should be 'abilities' or 'types'");
        }
        return list;
    }

    public static PokemonDetailSimpleResponse gimmeAValid() {
        return Fixture.from(PokemonDetailSimpleResponse.class).gimme(VALID);
    }

    public static List<PokemonDetailSimpleResponse> gimmeAValidList() {
        return Fixture.from(PokemonDetailSimpleResponse.class).gimme(2, VALID);
    }
}
