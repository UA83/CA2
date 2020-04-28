package com.example.wendigo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ImageButton buttonSend;

    ArrayList<Task> tasks = new ArrayList<Task>();
    Cursor cursor;

    TaskAdapter adapter = null;

    ListView listView;

    TasksDatabaseHandler db = new TasksDatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Populate Cursors
        cursor = db.getAllTasks();
        Log.i("INFO-DATA", "getting all: " + db.getAllTasks());

        // Store today's date
        String today = getDate();
        textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(today);

        getAdapterView();

        // Insert Item

        editTextInsertTask = findViewById(R.id.edit_text_insert_task);
        buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // It will always be 0, as when we insert a task, it was not done yet.
                String TASK_STATUS = "0";
                    Task insertTask = new Task(editTextInsertTask.getText().toString(), TASK_STATUS);
                    // Logcat
                    Log.i("INSERT", "Task Inserted: " + insertTask.getTask());

                    // Toast to let the user know that the task was added
                    Toast.makeText(MainActivity.this, "Task: "
                                    + insertTask.getTask() + "Inserted.",
                            Toast.LENGTH_SHORT).show();

                    editTextInsertTask.setText("");
                    closeKeyboard();
                    db.addTask(insertTask);
                    Log.i("ADAPTER", "adapter from onclick: " + adapter);
                    getAdapterView();

            }
        });












    }


    public void getAdapterView() {
        adapter = new TaskAdapter(this, cursor, 0);
        listView = findViewById(android.R.id.list);
        Log.i("ADAPTER", "adapter from on created: " + adapter);
        listView.setAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // Future work, create an arraylist to store these values? Maybe
        String taskID = cursor.getString(0);
        String task = cursor.getString(1);
        String status = cursor.getString(2);

        // Debbuging
        Log.i("INFO-CLICK-ITEM", "Here is what we got from the cursor per line : "
                + taskID + " task: " + task + " Status: " + status);

        Toast.makeText(MainActivity.this, "Content>>> ID : "
                + taskID + " task: " + task + " Status: " + status, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, ManageTaskActivity.class);
        intent.putExtra("taskID", taskID);
        intent.putExtra("taskName", task);
        intent.putExtra("taskStatus", status);
        // Start the new activity
        startActivity(intent);
    }

    public String getDate() {
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        return date_n;
    }

    // Deprecated, I started using this, but now I am using real input from user.
    public ArrayList<Task> getData() {
        tasks.add(new Task("Walk the Dog", "1"));
        tasks.add(new Task("Wash the Dog", "0"));
        tasks.add(new Task("Sing to the Dog", "0"));
        return tasks;
    }

    // Copied from https://codinginflow.com/tutorials/android/hide-soft-keyboard-programmatically
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}


















