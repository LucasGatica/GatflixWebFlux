package com.example.GatflixWebFlux.Repository;

import com.example.GatflixWebFlux.Model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository <Movie, String>{
}
