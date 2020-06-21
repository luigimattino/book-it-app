/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.controllers;

import it.lmattino.book.it.app.exceptions.OverlappingEventException;
import it.lmattino.book.it.app.forms.EventForm;
import it.lmattino.book.it.app.models.SpaceSlot;
import it.lmattino.book.it.app.models.TimeSlot;
import it.lmattino.book.it.app.models.TimeSlotIdentity;
import it.lmattino.book.it.app.repositories.SpaceSlotRepository;
import it.lmattino.book.it.app.services.TimeSlotService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mattinò
 */
@RestController
public class ApiController {

    @Autowired
    private SpaceSlotRepository spaceSlotRepo;
    
    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping(value = "/api/all-spaces")
    public List<SpaceSlot> getAllSpaceSlots() {
        return StreamSupport.stream(spaceSlotRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    
    @GetMapping(value = "/api/all-times")
    public List<TimeSlot> getAllTimeSlots(@RequestParam String date) {
        /*return StreamSupport.stream(timeSlotRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());*/
        return timeSlotService.findByDateString(date);
    }
    
    @PostMapping("/api/save-new-event")
    public ResponseEntity saveEvent(@RequestBody EventForm eventForm) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime startDT = LocalDateTime.of(eventForm.getDate(), eventForm.getStart());
        LocalDateTime endDT = LocalDateTime.of(eventForm.getDate(), eventForm.getEnd());
        
        TimeSlotIdentity identity = TimeSlotIdentity.builder()
                .spaceId(eventForm.getIdSpace())
                .startTime(startDT.atZone(zoneId).toInstant().toEpochMilli())
                .endTime(endDT.atZone(zoneId).toInstant().toEpochMilli())
                .build();
        SpaceSlot space = spaceSlotRepo.findById(eventForm.getIdSpace()).get();
        
        TimeSlot slot = TimeSlot.builder()
                .id(identity)
                .text(eventForm.getText())
                .space(space)
                .build();
        try {
            timeSlotService.saveTimeSlot(slot);
        } catch (OverlappingEventException ex) {
            return ResponseEntity.badRequest().body("L'evento si sovrappone ad uno già presente!");
        }
        
        return ResponseEntity.ok(slot);
    }
    
    @PutMapping("/api/update-event")
    public ResponseEntity updateEvent(@RequestBody EventForm eventForm) {
        SpaceSlot space = spaceSlotRepo.findByText(eventForm.getNameSpace());
        TimeSlotIdentity identity = TimeSlotIdentity.builder()
                .spaceId(space.getId())
                .startTime(eventForm.getStartDateTimeMillis())
                .endTime(eventForm.getEndDateTimeMillis())
                .build();
        TimeSlot slot = timeSlotService.findByIdentity(identity);
        slot.setText(eventForm.getText());
        timeSlotService.updateTimeSlot(slot);
        return ResponseEntity.ok(slot);
    }
    
    @DeleteMapping("/api/delete-event")
    public ResponseEntity deleteEvent(@RequestBody EventForm eventForm) {
        SpaceSlot space = spaceSlotRepo.findByText(eventForm.getNameSpace());
        TimeSlotIdentity identity = TimeSlotIdentity.builder()
                .spaceId(space.getId())
                .startTime(eventForm.getStartDateTimeMillis())
                .endTime(eventForm.getEndDateTimeMillis())
                .build();
        TimeSlot slot = timeSlotService.findByIdentity(identity);
        if ( timeSlotService.deleteTimeSlot(slot) )
            return ResponseEntity.ok(slot);
        else return ResponseEntity.notFound().build();
    }
}
