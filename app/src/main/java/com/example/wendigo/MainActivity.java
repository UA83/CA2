package com.example.wendigo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends ListActivity {


    // Top of the app from activity_layout
    TextView textViewAppName;
    TextView textViewDate;

    // Middle, where all the magic happens
    ListView listViewTaskContainer;

    // Bottom of the app from activity_layout
    EditText editTextInsertTask;
    Button buttonSend;

    ArrayList<Task> tasks = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Store today's date
        String today = getDate();
        textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(today);

        // Initialize a list of task. no db yet
        tasks = getData();

        WordAdapter adapter = new WordAdapter(this, tasks);

        ListView listView = findViewById(android.R.id.list);

        listView.setAdapter(adapter);

    }

    public ArrayList<Task> getData() {
        tasks.add(new Task("Walk the Dog", "1"));
        tasks.add(new Task("Wash the Dog", "0"));
        tasks.add(new Task("Sing to the Dog", "0"));

        return tasks;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(MainActivity.this, "Test : " + tasks.get(position).getTask(), Toast.LENGTH_SHORT).show();
        Intent mngIntent = new Intent(MainActivity.this, ManageTaskActivity.class);
        mngIntent.putExtra("taskName", tasks.get(position).getTask().toString());
        mngIntent.putExtra("taskStatus", tasks.get(position).getStatus().toString());
        // Start the new activity
        startActivity(mngIntent);
    }

    public String getDate() {

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

        return date_n;
    }

}


















