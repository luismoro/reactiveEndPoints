package com.br.itau.internet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveEndPoints {

//	@Bean
//	RouterFunction<?> router(PersonHandler handler) {
//		return RouterFunctions.route(RequestPredicates.GET("/persons"), handler::all)
//				.and(RouterFunctions.route(RequestPredicates.GET("/persons/{id}"), handler::byId));
//	}

//	@Bean
//	HttpServer server (RouterFunction<?> router) {
//		HttpHandler handler = RouterFunctions.toHttpHandler(router);
////		HttpServer httpServer = HttpServer.create(8080);
//		Tomcat
//		httpServer.newHandler(new ReactorHttpHandlerAdapter(handler));
//		return httpServer;
//	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveEndPoints.class, args);
	}
}

@Component
class PersonHandler {

	private final PersonRepository personRepository;

	@Autowired
	PersonHandler(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

//	ServerResponse<Flux<Person>> all(ServerRequest request) {
//		Flux<Person> flux = Flux.fromStream(personRepository.all());
//		return ServerResponse.ok().body(BodyInserters.fromPublisher(flux, Person.class));
//	}

	public Flux<Person> all() {
		return Flux.fromStream(personRepository.all());
	}

    public Mono<Person> byId(String id) {
        return Mono.fromFuture(personRepository.findById(id));
    }

//	ServerResponse<Mono<Person>> byId(ServerRequest request) {
//		String id = request.pathVariable("id");
//		return ServerResponse.ok().body(BodyInserters.fromPublisher(Mono.fromFuture(personRepository.findById(id)), Person.class));
//	}
}

@Component
class SampleDataCLR implements CommandLineRunner {

	private final PersonRepository personRepository;

	@Autowired
	public SampleDataCLR(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
//		Stream.of("Luis Moro", "Renan Barbioni", "André Formento", "Antonio Neto", "Emerson Muraro")
//		.forEach( name -> personRepository.save(new Person(name, new Random().nextInt(100))));
//
//		personRepository.findAll().forEach(System.out::println);
	}
}

interface PersonRepository extends MongoRepository<Person, String> {

	CompletableFuture<Person> findById(String id);

	@Query("{}")
	Stream<Person> all();
}

@Document
class Person {

	@Id
	private String id;

	private String name;

	private int age;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}