package com.br.itau.internet;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by luismoro on 12/12/16.
 */
interface PersonRepository extends MongoRepository<Person, String> {

    CompletableFuture<Person> findById(String id);

    @Query("{}")
    Stream<Person> all();
}
