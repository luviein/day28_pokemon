package com.example.day28_pokemon.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonRepo {
    
    @Autowired
    MongoTemplate template;

        /*
        * db.getCollection("pokemons").aggregate([
        { $unwind: "$type"},
        {$group : {
            _id: "$type",
            
        }}
        ])
        */
    public List<String> getPokemonTypes() {
        
        UnwindOperation unwindTypes = Aggregation.unwind("type");
        GroupOperation groupByType = Aggregation.group("type");
            // .push("name").as("pokemonName");
        SortOperation sortByType = Aggregation.sort(org.springframework.data.domain.Sort.by(Direction.ASC,"_id"));
        Aggregation pipeline = Aggregation.newAggregation(unwindTypes, groupByType, sortByType);

        return template.aggregate(pipeline, "pokemons", Document.class).getMappedResults()
        .stream()
        .map(d -> d.getString("_id"))
        .toList();
    

    }

        /*
         * db.getCollection("pokemons").aggregate([
            { $match: {type: "Grass"}},
            {
                $project:{
                    img:1, name:1, _id: 0
                }
            }
        ])
         */
        
        public List<Document> getPokemonNames(String pokeType) {
            MatchOperation matchType = Aggregation.match(Criteria.where("type").is(pokeType));
            ProjectionOperation summarisePokemon = Aggregation.project("name", "img", "type").andExclude("_id");
            Aggregation pipeline = Aggregation.newAggregation(matchType, summarisePokemon);
            return template.aggregate(pipeline, "pokemons", Document.class)
            .getMappedResults();
            
        }
}