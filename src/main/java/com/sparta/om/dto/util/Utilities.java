package com.sparta.om.dto.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Utilities {

    public static String DateConverter(LocalDate date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(date);
        return strDate;
    }
}
