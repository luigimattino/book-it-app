/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

/**
 *
 * @author Mattinò
 */
@Data
public class EventForm {
    private Long id;
    private String text;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern="HH:mm")
    private LocalTime start;
    @JsonFormat(pattern="HH:mm")
    private LocalTime end;
    private Long idSpace;
    private String nameSpace;
    private Long startDateTimeMillis;
    private Long endDateTimeMillis;
}
