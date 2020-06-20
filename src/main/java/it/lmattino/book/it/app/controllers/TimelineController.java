/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.controllers;

import it.lmattino.book.it.app.repositories.SpaceSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mattinò
 */
@Controller
@RequestMapping("/timeline")
public class TimelineController {
    
    @GetMapping
    public String timeline() {
        return "/timeline";
    }
    
}
