package com.example.day28_pokemon.service;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.example.day28_pokemon.model.Pokemon;

@Service
public class Utils {
    
    // public static Pokemon toPokemon(Document doc) {
    //     return new Pokemon(doc.getString("_id"));
    // }

        public static Pokemon toPokemonName(Document doc) {
            return new Pokemon(doc.getList("type", String.class), doc.getString("name"), doc.getString("img"));
        }
}
