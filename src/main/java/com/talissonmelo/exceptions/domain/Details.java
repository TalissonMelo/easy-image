package com.talissonmelo.exceptions.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record Details(String title,
                      Integer status,
                      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,

                      String message,
                      List<Field> errors) {
}
