/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.*;

/**
 *
 * @author Mattinò
 */
@Data
@Builder
@Entity
@Table(name = "time_slots")
@NoArgsConstructor@AllArgsConstructor
public class TimeSlot {
    @EmbeddedId
    private TimeSlotIdentity id;
    
    @NotBlank
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "space_slot_id", nullable = false)
    private SpaceSlot space;
}
