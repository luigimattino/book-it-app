/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.services;

import it.lmattino.book.it.app.exceptions.OverlappingEventException;
import it.lmattino.book.it.app.models.TimeSlot;
import it.lmattino.book.it.app.models.TimeSlotIdentity;
import it.lmattino.book.it.app.repositories.TimeSlotRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specification.where;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mattinò
 */
@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository repository;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository repository) {
        this.repository = repository;
    }

    static Specification<TimeSlot> hasSpaceId(Long id) {
        return (slot, cq, cb) -> cb.equal(slot.get("id").get("spaceId"), id);
    }

    static Specification<TimeSlot> isStartTimeEventBefore(Long time) {
        return (slot, cq, cb) -> cb.lessThan(slot.get("id").get("startTime"), time);
    }

    static Specification<TimeSlot> isEndTimeEventAfter(Long time) {
        return (slot, cq, cb) -> cb.greaterThan(slot.get("id").get("endTime"), time);
    }

    @Override
    public List<TimeSlot> findByDateString(String date) {
        LocalDate ldate = LocalDate.parse(date);
        LocalDateTime startOfDay = ldate.atStartOfDay();
        LocalDateTime endOfDay = ldate.atTime(LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        //startOfDay.atZone(zoneId).toEpochSecond();
        //endOfDay.atZone(zoneId).toEpochSecond();
        return StreamSupport.stream(repository.findAll(
                where(isStartTimeEventBefore(endOfDay.atZone(zoneId).toInstant().toEpochMilli()))
                        .and(isEndTimeEventAfter(startOfDay.atZone(zoneId).toInstant().toEpochMilli()))
        ).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSlot saveTimeSlot(TimeSlot timeSlot) throws OverlappingEventException {
        List<TimeSlot> list = repository.findAll(
                where(isStartTimeEventBefore(timeSlot.getId().getEndTime()))
                        .and(isEndTimeEventAfter(timeSlot.getId().getStartTime()))
                        .and(hasSpaceId(timeSlot.getId().getSpaceId()))
        );
        if (!list.isEmpty()) {
            //"L'evento \""+timeSlot.getText()+"\" non puo essere inserito perche la risorsa è già occupata"
            throw new OverlappingEventException();
        }

        repository.save(timeSlot);
        return timeSlot;
    }

    @Override
    public TimeSlot updateTimeSlot(TimeSlot timeSlot) {
        repository.save(timeSlot);
        return timeSlot;
    }

    @Override
    public boolean deleteTimeSlot(TimeSlot timeSlot) {
        repository.delete(timeSlot);
        return true;
    }

    @Override
    public TimeSlot findByIdentity(TimeSlotIdentity identity) {
        return repository.findById(identity).get();
    }

}
