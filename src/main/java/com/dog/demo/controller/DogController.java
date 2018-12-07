package com.dog.demo.controller;

import com.dog.demo.dao.DogRepository;
import com.dog.demo.model.Dog;
import com.dog.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.ModalExclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DogController {

    private DogRepository dogRepository;
    private DogService dogService;

    @Autowired
    public DogController(DogRepository dogRepository, DogService dogService) {
        this.dogRepository = dogRepository;
        this.dogService = dogService;
    }

    @GetMapping("/")
    public String doghome (Model model){

        List<Dog> all = dogRepository.findAll();
        model.addAttribute("dogs", all);

        return "index";

    }

    @PostMapping ("/")
    public String adddog (@RequestParam ("name") String name,
                          @RequestParam("rescued") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @RequestParam ("vaccinated") Boolean vaccinated, Model model) {
        dogService.addDog(name, date, vaccinated);

        return "redirect:/";

    }

    @PostMapping ("/delete")
    public String deleteDog (
                           @RequestParam ("id") Long id) {


       dogService.deleteDog(id);

        //dogRepository.delete(id);

        return "redirect:/";
    }

    @GetMapping ("/add")
    public String doghome (@RequestParam(value = "search", required = false)
                           @DateTimeFormat (pattern = "yyyy-MM-dd") Date date,Model model){

        List<Dog> findDog = new ArrayList<>();
        if (date != null){
            List<String> dogsByName = dogService.listDogs(date);
            dogsByName.forEach(dog->findDog.add(dogRepository.findByName(dog)));


        }

        model.addAttribute("search",findDog);
        List<Dog> all = dogRepository.findAll();
        model.addAttribute("dogs", all);

        return "index";
    }
}
