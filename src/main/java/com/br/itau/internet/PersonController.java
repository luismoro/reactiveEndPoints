package com.br.itau.internet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import reactor.core.publisher.Flux;

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
    public CompletableFuture<Person> getAllPersons(@PathVariable("id") String id) {
        return personService.byId(id).toFuture();
    }

}