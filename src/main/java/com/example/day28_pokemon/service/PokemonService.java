package com.example.day28_pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.day28_pokemon.model.Pokemon;
import com.example.day28_pokemon.repository.PokemonRepo;

@Service
public class PokemonService {

    @Autowired
    PokemonRepo pokeRepo;

    public List<String> getPokemonTypes() {
        List<String>pokeType = pokeRepo.getPokemonTypes();
        System.out.println(pokeType);
        // List<Pokemon> pokemon = new ArrayList<>();
        // for(Document d : pokeType) {
        //     pokemon.add(Utils.toPokemon(d));
        // }

        return pokeType;
    }

    public List<Pokemon> getPokemonNames(String pokeType) {
        List<Document>pokeName = pokeRepo.getPokemonNames(pokeType);
        List<Pokemon> pokemon = new ArrayList<>();
        for(Document d : pokeName) {
            pokemon.add(Utils.toPokemonName(d));
        }
        System.out.println("POKEMON:>>>>>>>"+pokemon);
        return pokemon;
    }
}
