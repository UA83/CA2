package com.example.wendigo;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskAdapter extends CursorAdapter {

    private String TAG = "DEBUG";

    private Cursor mCursor;

    private LayoutInflater cursorInflater;


    // Default constructor
    public TaskAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

            cursorInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
    }


    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_task_name);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_row);
        String title = cursor.getString( cursor.getColumnIndex(TasksDatabaseHandler.TASK));
        String title1 = cursor.getString( cursor.getColumnIndex(TasksDatabaseHandler.STATUS));
        textViewTitle.setText(title);

        if(title1.equals("1")) {
            textViewTitle.setTextColor(Color.GRAY);
            linearLayout.setBackgroundColor(Color.LTGRAY);
        }

        Log.i(TAG, "bindView: " + title1);
    }


    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // R.layout.list_row is your xml layout for each row
        return cursorInflater.inflate(R.layout.row, parent, false);
    }


    public int getItemCount() {
        return mCursor.getCount();
    }

}
