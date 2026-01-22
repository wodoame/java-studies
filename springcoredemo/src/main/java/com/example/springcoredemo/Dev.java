package com.example.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dev {

    @Autowired // field injection
    private Computer computer;

    public void build(){
        computer.compile();
        System.out.println("Building software");
    }
}
