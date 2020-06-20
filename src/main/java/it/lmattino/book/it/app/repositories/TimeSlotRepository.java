/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.repositories;

import it.lmattino.book.it.app.models.TimeSlot;
import it.lmattino.book.it.app.models.TimeSlotIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mattinò
 */
@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, TimeSlotIdentity>, JpaSpecificationExecutor<TimeSlot> {
    
}
