package za.co.sabs.planningtool.utils;

import java.time.format.DateTimeFormatter;

public class DateFormatUtil {
    public static DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
