package com.example.day28_pokemon.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.day28_pokemon.model.Pokemon;
import com.example.day28_pokemon.repository.PokemonRepo;
import com.example.day28_pokemon.service.PokemonService;


@Controller
@RequestMapping(path="/types")
public class PokemonController {
    
    @Autowired
    PokemonService pokeSvc;

    @Autowired
    PokemonRepo pokeRepo;

    @GetMapping()
    public ModelAndView getIndex() {
        List<String> pokemons = pokeSvc.getPokemonTypes();
        
        System.out.println(">>>>>>>>"+pokemons);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("poketype", pokemons);
        return mav;
    }

        @GetMapping(path="/{typePoke}")
        public ModelAndView getPokemon(@PathVariable String typePoke) {

            List<Pokemon> pokemonName = pokeSvc.getPokemonNames(typePoke);
            for(Pokemon mon : pokemonName) {
                System.out.println(mon);
            }

            // List<String> name = pokemonName.stream()
            //     .map(d -> d.getString("name"))
            //     .toList();
            // List<String> img = pokemonName.stream()
            //     .map(d -> d.getString("img"))
            //     .toList();

            ModelAndView mav = new ModelAndView();
            mav.setViewName("pokeType");
            mav.addObject("list", pokemonName);
            mav.addObject("type", typePoke);
            // mav.addObject("img", img);
            return mav;
        }
}
