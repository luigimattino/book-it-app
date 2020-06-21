/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mattinò
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    String getView(Model model) {
        model.addAttribute("msg", 
                "Hello there, Gestione di attività in spazi ricreativi: Per gestire l'allocamento temporale di risorse, in modo generale ho definito i concetti di spazio fisico e temporale come \"slot\" (\"time_slot\" e \"space_slot\")."
        );
        return "home";
    }
}
