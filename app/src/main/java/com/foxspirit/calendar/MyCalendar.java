package com.foxspirit.calendar;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {
    private final TextView calendarDisplayDate;
    private final TextView calendarDisplayWeekDay;
    private final TextView calendarDisplayMonth;
    private final TextView calendarDisplayYear;
    private final GridView calendarGrid;

    private static Calendar calendar;

    private final ArrayList<Integer> dates = new ArrayList<>();

    private final TextView tv;

    private final ArrayList<Integer> dayOfTheWeek = new ArrayList<>();
    private final ArrayList<Integer> quantityOfDaysInMonth = new ArrayList<>();
    private final ArrayList<Integer> daysOffset = new ArrayList<>();

    int currentDate;
    int currentMonth;
    int currentYear;

    public ArrayList<Integer> getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public ArrayList<Integer> getQuantityOfDaysInMonth() {
        return quantityOfDaysInMonth;
    }

    public ArrayList<Integer> getDaysOffset() {
        return daysOffset;
    }

    public MyCalendar(View calendarDisplayDate, View calendarDisplayWeekDay, View calendarDisplayMonth, View calendarDisplayYear, View calendarGrid, View tv) {
        this.calendarDisplayDate = (TextView) calendarDisplayDate;
        this.calendarDisplayWeekDay = (TextView) calendarDisplayWeekDay;
        this.calendarDisplayMonth = (TextView) calendarDisplayMonth;
        this.calendarDisplayYear = (TextView) calendarDisplayYear;
        this.calendarGrid = (GridView) calendarGrid;
        calendar = new GregorianCalendar();
        this.tv = (TextView) tv;

        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.YEAR, i, 1);
            quantityOfDaysInMonth.add(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            dayOfTheWeek.add(calendar.get(Calendar.DAY_OF_WEEK));
            daysOffset.add(setOffset(calendar.get(Calendar.DAY_OF_WEEK)));
        }
        calendar = Calendar.getInstance();

        currentDate = Calendar.getInstance().get(Calendar.DATE);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Calendar getCalendar() {
        return calendar;
    }

    public ArrayList<Integer> getDates() {
        return dates;
    }

    public void setDate(int date) {
        calendar.set(Calendar.DATE, date);
    }

    public Integer setOffset(int dayOfTheWeek) {
        int offset = 0;
        switch (dayOfTheWeek) {
            case 1:
                offset = -1;
                break;
            case 2:
                offset = 0;
                break;
            case 3:
                offset = 1;
                break;
            case 4:
                offset = 2;
                break;
            case 5:
                offset = 3;
                break;
            case 6:
                offset = 4;
                break;
            case 7:
                offset = 5;
                break;
        }
        return offset;
    }

    public void fillDays() {
        int thisMonth = calendar.get(Calendar.MONTH);
        int offset = daysOffset.get(thisMonth);
        for (int i = -offset; i <= quantityOfDaysInMonth.get(thisMonth); i++) {
            if (i < 1 || i > quantityOfDaysInMonth.get(thisMonth)) {
                dates.add(0);
                continue;
            }
            dates.add((i));
        }
    }

    public String getCurrentDay() {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return "Wrong day of week";
    }

    public String getCurrentYear() {
        return ((Integer) calendar.get(Calendar.YEAR)).toString();
    }

    public String getCurrentMonth() {
        switch (calendar.get(Calendar.MONTH)) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "Wrong month";
        }
    }

    public String getCurrentDate() {
        return ((Integer) calendar.get(Calendar.DATE)).toString();
    }

    public String getNextDate() {
        calendar.add(Calendar.DATE, 1);
        updateFields();
        return getCurrentDate();
    }

    public String getPreviousDate() {
        calendar.add(Calendar.DATE, -1);
        updateFields();
        return getCurrentDate();
    }

    public void toToday() {
        calendar.set(currentYear, currentMonth, currentDate);
    }

    public void updateFields() {
        calendarDisplayDate.setText(getCurrentDate());
        calendarDisplayWeekDay.setText(getCurrentDay());
        if (getCurrentMonth() != calendarDisplayMonth.getText()) {
            calendarDisplayMonth.setText(getCurrentMonth());
            dates.clear();
            fillDays();
        }
        if (getCurrentYear() != calendarDisplayYear.getText()) {
            calendarDisplayYear.setText(getCurrentYear());
        }

        calendarGrid.setAdapter(new MyCalendarAdapter(calendarGrid.getContext(), getDates(), getCurrentDate(), daysOffset.get(calendar.get(Calendar.MONTH))));
    }


}