package com.training.requesttracker.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by sony on 7/22/2017.
 */
public class TimeUnit implements Comparable<TimeUnit> {
    private int hour;
    private int minute;

    private Calendar calendar = Calendar.getInstance();

    public TimeUnit(Date date) {
        calendar.setTime(date);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeUnit timeUnit = (TimeUnit) o;

        if (hour != timeUnit.hour) return false;
        return minute == timeUnit.minute;
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        return result;
    }

    @Override
    public String toString() {
        return "hour=" + hour + ", minute=" + minute;
    }



    @Override
    public int compareTo(TimeUnit o) {
        if (o.hour != this.hour)
            return this.hour > o.hour ? 1 : -1;
        else if (o.minute != this.minute)
            return this.minute > o.minute ? 1 : -1;

        return 0;
    }

    public int diffInMinutes(Date date) {
        calendar.setTime(date);
        int dateHour = calendar.get(Calendar.HOUR_OF_DAY);
        int dateMinute = calendar.get(Calendar.MINUTE);
        int dateMinutes = dateHour*60 + dateMinute;

        int timeUnitMinutes = hour*60 + minute;

        return dateMinutes - timeUnitMinutes;
    }

}
