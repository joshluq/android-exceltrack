package pe.exceltransport.exceltrack.view.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {

    private static final Locale locale = new Locale("es", "pe");

    public static final String DEFAULT_FORMAT ="dd-MM-yyyy h:mm a";

    public static String milliSecondsToDateFormatted(long milliSeconds, String dateFormat){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return new SimpleDateFormat(dateFormat,locale).format(calendar.getTime());
    }

}
