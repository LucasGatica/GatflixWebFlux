package com.example.GatflixWebFlux.Controller;

import com.example.GatflixWebFlux.Model.Movie;
import com.example.GatflixWebFlux.Model.MovieEvent;
import com.example.GatflixWebFlux.Repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/filmes")
public class MovieController {

    private MovieRepository repository;
    public MovieController(MovieRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public Flux<Movie> getAllMovies(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Movie>> getMovie(@PathVariable String id){
        return repository.findById(id)
                .map(movie -> ResponseEntity.ok(movie))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> saveMovie(@RequestBody Movie movie){
        return repository.save(movie);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable(value = "id") String id,
                                                   @RequestBody Movie movie){
        return repository.findById(id)
                .flatMap(existingMovie ->{
                    existingMovie.setName(movie.getName());
                    existingMovie.setDescription((movie.getDescription()));
                    existingMovie.setYear(movie.getYear());
                    existingMovie.setRating(movie.getRating());

                    return repository.save(existingMovie);
                })
                .map(updateMovie -> ResponseEntity.ok(updateMovie))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteMovie(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .flatMap(existingMovie ->
                        repository.delete(existingMovie)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllMovies(){return repository.deleteAll();}

    @GetMapping(value = "/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getMovieEvents(){
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new MovieEvent(val,"filmes"));
    }
}
