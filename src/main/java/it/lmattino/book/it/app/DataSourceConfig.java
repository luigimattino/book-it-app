/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app;

import it.lmattino.book.it.app.models.SpaceSlot;
import it.lmattino.book.it.app.repositories.SpaceSlotRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mattinò
 */
@Slf4j
@Configuration
public class DataSourceConfig {
    
    @Autowired
    private SpaceSlotRepository spaceSlotRepo;
    
    @Bean
    CommandLineRunner initDatabase() {
        return (String[] args) -> {
            String[] slotArray = {
                //"Auditorium A1", "Auditorium B1", "Auditorium C1", "Auditorium D1",
                //"Auditorium A2", "Auditorium B2", "Auditorium C2", "Auditorium D2",
                "Auditorium E", "Auditorium F", "Auditorium G", "Auditorium H",
                "Auditorium I", "Auditorium L", "Auditorium M", "Auditorium N"
            };
            List<SpaceSlot> collect1 = Stream.of(slotArray)
                    .map(SpaceSlot::new).collect(Collectors.toList());
            collect1.forEach(slot -> spaceSlotRepo.save(slot));
        };
        
    }
    
}
