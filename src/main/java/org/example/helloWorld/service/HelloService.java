package org.example.helloWorld.service;

import org.example.helloWorld.model.Hello;
import org.example.helloWorld.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    @Autowired
    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public void createFile(Hello hello){
        helloRepository.init(hello);
    }


}
