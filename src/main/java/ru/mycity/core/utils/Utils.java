package ru.mycity.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mycity.core.service.dao.model.DateTimeModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static DateTimeModel getDateTime(String startDate, String endDate) throws ParseException {
        return getDateTime(createDate(startDate), createDate(endDate));
    }


    public static Date createDate(String date) throws ParseException {
        if(date != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        }
       return null;
    }

    public static DateTimeModel getDateTime(Date startDate, Date endDate) {
        Timestamp start = startDate != null ? getTimestamp(startDate, 0, 0) : null;
        Timestamp end = endDate != null ? getTimestamp(endDate, 23, 59) : null;
        return new DateTimeModel(start, end);
    }


    public static Timestamp getTimestamp(Date date, int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, hour);
        calendar.set(12, minutes);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp createTimestampNow() {
        return new Timestamp(System.currentTimeMillis());
    }
}
