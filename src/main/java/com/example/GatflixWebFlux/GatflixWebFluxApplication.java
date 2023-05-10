package com.example.GatflixWebFlux;

import com.example.GatflixWebFlux.Model.Movie;
import com.example.GatflixWebFlux.Repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;


@SpringBootApplication
public class GatflixWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatflixWebFluxApplication.class, args);
	}


		@Bean
			CommandLineRunner init (ReactiveMongoOperations operations, MovieRepository repository){
			return args ->{
				Flux<Movie> movieFlux = Flux.just(
						new Movie(null,"Whiplash",2015,
								"Um jovem baterista sonha em ser o melhor de sua geração." +
										" Com o treinamento de um mestre impiedoso, o músico " +
										"começa a ultrapassar todos os seus limites, inclusive tomando " +
										"atitudes que jamais pensou que tomaria.",10.0),
						new Movie(null, "gran torino",2008,
								"Walt Kowalski, um trabalhador aposentado e veterano da Guerra da Coreia," +
										" preenche sua vida com cerveja e reparos em casa," +
										" desprezando famílias asiáticas," +
										" negras e latinas no bairro histórico onde mora." +
										" Ele se torna um herói relutante quando fica ao lado de um adolescente" +
										" asiático que foi forçado por uma gangue a roubar seu precioso automóvel, " +
										"um Gran Torino. Uma improvável amizade se desenvolve entre os dois, e Walt " +
										"aprende que tem muito em comum com os vizinhos asiáticos.", 9.5))
								.flatMap(repository::save);
				movieFlux
						.thenMany(repository.findAll())
						.subscribe(System.out::println);

			};

	}

}
