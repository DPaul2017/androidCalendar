package com.foxspirit.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView calendarDisplayWeekDay;
    private Button calendarGoToTodayButton;

    MyCalendar mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        ImageView calendarPreviewButton = (ImageView) findViewById(R.id.calendar_prev_button);
        ImageView calendarNextButton = (ImageView) findViewById(R.id.calendar_next_button);
        TextView calendarDisplayDate = (TextView) findViewById(R.id.date_display_date);
        TextView calendarDisplayMonth = (TextView) findViewById(R.id.date_display_month);
        TextView calendarDisplayYear = (TextView) findViewById(R.id.date_display_year);
        calendarDisplayWeekDay = (TextView) findViewById(R.id.date_display_day);
        calendarGoToTodayButton = (Button) findViewById(R.id.date_display_today);
        GridView calendarGrid = (GridView) findViewById(R.id.calendar_grid);

        TextView tv = findViewById(R.id.textView);
        calendarPreviewButton.setOnClickListener(this);
        calendarNextButton.setOnClickListener(this);
        calendarDisplayDate.setOnClickListener(this);
        calendarDisplayMonth.setOnClickListener(this);
        calendarDisplayYear.setOnClickListener(this);
        calendarDisplayWeekDay.setOnClickListener(this);
        calendarGoToTodayButton.setOnClickListener(this);
        calendarGrid.setOnItemClickListener((parent, view, position, id) -> {
            parent.getItemAtPosition(position);
            String text = ((TextView) view).getText().toString();
        });

        mc = new MyCalendar(calendarDisplayDate, calendarDisplayWeekDay, calendarDisplayMonth, calendarDisplayYear, calendarGrid, tv);
        mc.updateFields();

        tv.setText(mc.getQuantityOfDaysInMonth() + "\n" + mc.getDayOfTheWeek() + "\n" + mc.getDaysOffset());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_display_today:
                mc.getCalendar().add(Calendar.DATE, -mc.getDateChange());
                mc.setDateChange(0);
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
        }
    }

    private void viewToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
