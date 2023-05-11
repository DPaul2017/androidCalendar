package com.foxspirit.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView calendarPreviewButton;
    ImageView calendarNextButton;
    TextView calendarDisplayDate;
    TextView calendarDisplayMonth;
    TextView calendarDisplayYear;
    TextView calendarDisplayWeekDay;
    ImageView calendarGoToTodayButton;
    GridView calendarGrid;
    TextView tv;
    MyCalendar mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        initVars();
        setListeners();

        mc = new MyCalendar(calendarDisplayDate, calendarDisplayWeekDay, calendarDisplayMonth, calendarDisplayYear, calendarGrid, tv);
        mc.updateFields();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_display_today:
                mc.toToday();
                mc.updateFields();
                break;
            case R.id.calendar_prev_button:
                mc.getPreviousDate();
                break;
            case R.id.calendar_next_button:
                mc.getNextDate();
                break;
            default:
                viewToastMessage("Not set");
                break;
        }
    }

    private void viewToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initVars() {
        calendarPreviewButton = (ImageView) findViewById(R.id.calendar_prev_button);
        calendarNextButton = (ImageView) findViewById(R.id.calendar_next_button);
        calendarDisplayDate = (TextView) findViewById(R.id.date_display_date);
        calendarDisplayMonth = (TextView) findViewById(R.id.date_display_month);
        calendarDisplayYear = (TextView) findViewById(R.id.date_display_year);
        calendarDisplayWeekDay = (TextView) findViewById(R.id.date_display_day);
        calendarGoToTodayButton = (ImageView) findViewById(R.id.date_display_today);
        calendarGrid = (GridView) findViewById(R.id.calendar_grid);
        tv = findViewById(R.id.textView);
    }

    private void setListeners() {
        calendarPreviewButton.setOnClickListener(this);
        calendarNextButton.setOnClickListener(this);
        calendarDisplayDate.setOnClickListener(this);
        calendarDisplayMonth.setOnClickListener(this);
        calendarDisplayYear.setOnClickListener(this);
        calendarDisplayWeekDay.setOnClickListener(this);
        calendarGoToTodayButton.setOnClickListener(this);
        calendarGrid.setOnItemClickListener((parent, view, position, id) -> {
            Integer date = (Integer) parent.getItemAtPosition(position);
            String text = ((TextView) view).getText().toString();
            mc.setDate(date);
            mc.updateFields();
        });
    }


}
