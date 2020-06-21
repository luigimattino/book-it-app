/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.services;

import it.lmattino.book.it.app.exceptions.OverlappingEventException;
import it.lmattino.book.it.app.models.TimeSlot;
import it.lmattino.book.it.app.models.TimeSlotIdentity;
import java.util.List;

/**
 *
 * @author Mattinò
 */
public interface TimeSlotService {
    TimeSlot findByIdentity(TimeSlotIdentity identity);
    List<TimeSlot> findByDateString(String date);
    TimeSlot saveTimeSlot(TimeSlot timeSlot) throws OverlappingEventException;
    TimeSlot updateTimeSlot(TimeSlot timeSlot);
    boolean deleteTimeSlot(TimeSlot timeSlot);
}
