package com.br.itau.internet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by luismoro on 13/12/16.
 */
@Component
public class SampleDataCLR implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Autowired
    public SampleDataCLR(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
//		Stream.of("Teste", "Renan Barbioni", "André Formento", "Antonio Neto", "Emerson Muraro")
//		.forEach( name -> personRepository.save(new Person(name, new Random().nextInt(100))));
//
//		personRepository.findAll().forEach(System.out::println);
    }
}
