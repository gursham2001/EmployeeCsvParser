package com.sparta.om.dto.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static Date DateConverter(LocalDate date){
        return Date.valueOf(date);
    }
}
