package com.dog.demo.service;

import com.dog.demo.dao.DogRepository;
import com.dog.demo.model.Dog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    private JdbcTemplate jdbcTemplate;

    public DogServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer addDog(String name, Date rescued, Boolean vaccinated) {
        Dog dog = new Dog();
        return jdbcTemplate.update("INSERT INTO dog (name, rescued,vaccinated)" +
                        "VALUES (?,?,?)",name, rescued, vaccinated);

    }

    @Override
    public void deleteDog(Long id) {
        jdbcTemplate.update("delete from dog where id =?", id);

    }

    @Override
    public List<String> listDogs(Date rescued) {
        String sql = "select * from dog where vaccinated ='0' and rescued <'" + rescued+ "'";
        List<String> dogList = new ArrayList<>();
        jdbcTemplate.query(sql,(ResultSetExtractor<List>) rs -> {
            while (rs.next()) {
                String name = rs.getString("name");
                dogList.add(name);
            }
            return dogList;

        });
        return dogList;
    }
}
