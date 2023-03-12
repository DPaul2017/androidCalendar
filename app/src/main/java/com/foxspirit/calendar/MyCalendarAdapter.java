package com.foxspirit.calendar;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MyCalendarAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Integer> arrayList;
    private final String currentDate;
    private final int offset;

    public MyCalendarAdapter(Context context, ArrayList objects, String currentDate, int offset) {
        this.context = context;
        this.arrayList = objects;
        this.currentDate = currentDate;
        this.offset = offset;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            textView = new TextView(context);
            if (!getItem(position).equals(0)) textView.setText(getItem(position).toString());
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setHeight(100);
            textView.setWidth(100);
            if (position == Integer.parseInt(currentDate) + offset) {
                textView.setBackgroundColor(0x55aaaaaa);
                textView.setTextSize(20);
                textView.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD_ITALIC);
            }
        } else {
            textView = (TextView) convertView;
        }
        textView.setId(position);
        return textView;
    }
}
