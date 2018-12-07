package com.dog.demo.service;

import java.util.Date;
import java.util.List;


public interface DogService  {
    Integer addDog (String name, Date rescued, Boolean vaccinated);
    void deleteDog (Long id);

    List<String> listDogs (Date rescued);
}
