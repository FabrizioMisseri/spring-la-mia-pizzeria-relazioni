package org.learning.java.springlamiapizzeriacrud.controller;

import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.java.springlamiapizzeriacrud.service.PizzaService;
import org.learning.java.springlamiapizzeriacrud.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/special-offer")
public class SpecialOfferController {

    @Autowired
    PizzaService pizzaService;

    @Autowired
    SpecialOfferService specialOfferService;


    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId") Optional<Integer> id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setStartingDate(LocalDate.now());
        specialOffer.setEndingDate(LocalDate.now().plusMonths(1));
        if (id.isPresent()) {
            Pizza pizza = pizzaService.getById(id.get());
            specialOffer.setPizza(pizza);
        }
        model.addAttribute("specialOffer", specialOffer);
        return "/special-offer/create";
    }


}
