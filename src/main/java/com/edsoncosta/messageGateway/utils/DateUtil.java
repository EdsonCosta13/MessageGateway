package com.edsoncosta.messageGateway.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDateTime strToLocalDateTime(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
            return LocalDateTime.of(date, LocalTime.MIN);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida, formato esperado: dd-MM-yyyy");
        }
    }
}
