package com.sparta.om.dto.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {

    public static Date DateConverter(LocalDate date){
//        System.out.println(date);
//        String formattedDate = date.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
//        System.out.println(formattedDate);
//        return formattedDate;
        Date formattedDate = Date.valueOf(date); // Magic happens here!
        return formattedDate;
    }
}
