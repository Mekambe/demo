package com.dog.demo.dao;

import com.dog.demo.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog,Long> {

    Dog findByName (String name);
}
