package org.learning.java.springlamiapizzeriacrud.controller;


import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.PizzaRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRpository pizzaRpository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {

//        List<Book> books;
//        if (keyword.isEmpty()) {
//            books = bookRepository.findAll(Sort.by("title"));
//        } else {
//            books = bookRepository.findByTitleContainingIgnoreCase(keyword.get());
//            model.addAttribute("keyword", keyword.get());
//        }
//        model.addAttribute("list", books);

        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaRpository.findAll();
        } else {
            pizzas = pizzaRpository.findByNameContainingIgnoreCase(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("allPizza", pizzas);
        return "/pizzas/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza pizza = pizzaRpository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("pizza", pizza);
        return "/pizzas/show";
    }

}
