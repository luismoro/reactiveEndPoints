package com.br.itau.internet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by luismoro on 05/12/16.
 */
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = {"/persons"}, method = RequestMethod.GET)
    public Flux<Person> getAllPersons() {
        return personService.all();
    }

    @RequestMapping(value = {"/persons/{id}"}, method = RequestMethod.GET)
    public Mono<Person> getPerson(@PathVariable("id") String id) {
        return personService.byId(id);
    }

    @RequestMapping(value = {"/persons"}, method = RequestMethod.POST)
    public Person createPerson(@RequestBody Person person) {
        return personService.save(person);
    }

    @RequestMapping(value = {"/persons/{id}"}, method = RequestMethod.PATCH)
    public Mono<Person> updatePersonPath(@PathVariable("id") String id, @RequestBody Person person) {
        return personService.justSave(id, person);
    }

    @RequestMapping(value = {"/persons/{id}"}, method = RequestMethod.PUT)
    public Person updatePersonPut(@PathVariable("id") String id, @RequestBody Person person) {
        return personService.save(id, person);
    }
}