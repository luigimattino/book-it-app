/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mattinò
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "space_slots")
public class SpaceSlot {
    private @Id
    @GeneratedValue
    Long id;
    
    @NotBlank
    private String text;

    public SpaceSlot(String text) {
        this.text = text;
    }
}
