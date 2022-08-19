package com.sparta.om.logging;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return LocalDate.now()
                + " - "+ LocalTime.now()
                + " " + record.getLevel()
                + " " + record.getMessage()
                + " " + record.getSourceClassName()
                + " \n";
    }
}
