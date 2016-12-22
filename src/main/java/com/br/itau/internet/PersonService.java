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

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person save(String id, Person person) {
        Person p = new Person(id, person.getName(), person.getAge());

        return personRepository.save(p);
    }

    public Mono<Person> justSave(String id, Person person) {
        Mono<Person> mono = Mono.fromFuture(personRepository.findById(id));

        return mono.map(p -> {
            Person personUpdated = new Person(id, person.getName(), person.getAge(), p);
            return personRepository.save(personUpdated);
        });
    }
}
