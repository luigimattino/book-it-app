/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.controllers;

import it.lmattino.book.it.app.models.SpaceSlot;
import it.lmattino.book.it.app.repositories.SpaceSlotRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Mattinò
 */
@Controller
public class SpaceSlotController {
    
    @Autowired
    private SpaceSlotRepository spaceSlotRepo;
    
    
    @GetMapping("/space-slots")
    public String allSpaceSlots(Model model) {
        model.addAttribute("slots", spaceSlotRepo.findAll());
        return "/space-slots";
    }
    
    @GetMapping("/new-space-slot")
    public String showSpaceSlotForm(Model model) {
        model.addAttribute("slot", new SpaceSlot());
        return "/new-space-slot";
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addSpaceSlot(@Valid @ModelAttribute SpaceSlot slot, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/new-space-slot";
        }
        spaceSlotRepo.save(slot);
        model.addAttribute("slots", spaceSlotRepo.findAll());
        return "/space-slots";
    }
    
    @GetMapping("space-slot/{id}")
    public String showSpaceSlotById(@PathVariable Long id, Model model) {
        SpaceSlot slot = spaceSlotRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid slot Id:" + id));
        model.addAttribute("slot", slot);
        return "/edit-space-slot";
    }
    

    @PostMapping("space-slot/{id}/update")
    public String updateSpaceSlot(@PathVariable Long id, @Valid @ModelAttribute SpaceSlot slot, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/edit-space-slot";
        }
        spaceSlotRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid slot Id:" + id));
        spaceSlotRepo.save(slot);
        model.addAttribute("slots", spaceSlotRepo.findAll());
        return "/space-slots";
    }

    @PostMapping("space-slot/{id}/delete")
    public String deleteSpaceSlot(@PathVariable Long id, Model model) {
        spaceSlotRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid slot Id:" + id));
        spaceSlotRepo.deleteById(id);
        model.addAttribute("slots", spaceSlotRepo.findAll());
        return "/space-slots";
    }
}
