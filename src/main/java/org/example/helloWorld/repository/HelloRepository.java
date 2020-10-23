package org.example.helloWorld.repository;

import org.example.helloWorld.model.Hello;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
public class HelloRepository {
    private ArrayList<Hello> hellos = new ArrayList<>();

    public void init(Hello hello){
        hellos.add(hello);
    }

    public ArrayList<Hello> getHellos() {
        return hellos;
    }

    public void setHellos(ArrayList<Hello> hellos) {
        this.hellos = hellos;
    }
}
