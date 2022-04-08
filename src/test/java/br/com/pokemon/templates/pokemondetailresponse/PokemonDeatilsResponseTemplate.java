package br.com.pokemon.templates.pokemondetailresponse;

import br.com.pokemon.domain.PokemonDetail;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokemonDeatilsResponseTemplate implements TemplateLoader {
    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(PokemonDetail.class).addTemplate(VALID, new Rule() {{
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

    public static List<PokemonDetail> gimmeAValidList() {
        return Fixture.from(PokemonDetail.class).gimme(2, VALID);
    }

    public static PokemonDetail gimmeAValid() {
        return Fixture.from(PokemonDetail.class).gimme(VALID);
    }
}
