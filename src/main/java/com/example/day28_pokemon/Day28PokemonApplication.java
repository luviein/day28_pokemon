package com.example.day28_pokemon;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.day28_pokemon.model.Pokemon;
import com.example.day28_pokemon.repository.PokemonRepo;
import com.example.day28_pokemon.service.PokemonService;

@SpringBootApplication
public class Day28PokemonApplication implements CommandLineRunner{

	@Autowired
	PokemonRepo pokeRepo;

	@Autowired 
	PokemonService pokeSvc;
	public static void main(String[] args) {
		SpringApplication.run(Day28PokemonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Pokemon> result = pokeSvc.getPokemonNames("Poison");

		System.out.println(result);
		for(Pokemon d : result) {
			System.out.printf(">>>>> Name: %s\n", d);
		}


	}

}
