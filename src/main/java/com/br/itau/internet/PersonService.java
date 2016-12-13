package com.br.itau.internet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by luismoro on 12/12/16.
 */
@Component
class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Flux<Person> all() {
		return Flux.fromStream(personRepository.all());
	}

    public Mono<Person> byId(String id) {
		return Mono.fromFuture(personRepository.findById(id));
    }

}
