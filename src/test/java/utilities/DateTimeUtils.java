package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public String convertDateTo(String date,String curZone){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        ZoneId currentZone = ZoneId.of(curZone.split(" ")[1]);
        LocalDateTime zoneTime = LocalDateTime.parse(date,inputFormatter);
        ZonedDateTime zdt = ZonedDateTime.of(zoneTime, currentZone);
        ZoneId toTimeZone = ZoneId.of("Asia/Calcutta");
        ZonedDateTime convertedDate = zdt.withZoneSameInstant(toTimeZone);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        String convertedDateTime = convertedDate.format(outputFormatter);
        System.out.println(convertedDateTime);
        return convertedDateTime;
    }

    public String formatDate(String dayOfMonth, String time){
        Date now = new Date();
        String dateStr = String.format("%02d/%02d/%d ",Integer.parseInt(dayOfMonth), now.getMonth()+1, now.getYear()+1900)+time;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        DateFormat outputformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date= df.parse(dateStr);
            return outputformat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
