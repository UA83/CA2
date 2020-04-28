package com.example.wendigo;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskAdapter extends CursorAdapter {

    private LayoutInflater cursorInflater;


    // Default constructor
    public TaskAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

            cursorInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
    }


    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_task_name);
        String title = cursor.getString( cursor.getColumnIndex(TasksDatabaseHandler.TASK));
        textViewTitle.setText(title);
    }


    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // R.layout.list_row is your xml layout for each row
        return cursorInflater.inflate(R.layout.row, parent, false);
    }

}
