/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.models;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.*;

/**
 *
 * @author Mattinò
 */
@Data
@Builder
@Embeddable
@NoArgsConstructor@AllArgsConstructor
public class TimeSlotIdentity implements Serializable {
    private Long spaceId;
    private Long startTime;
    private Long endTime;
}
