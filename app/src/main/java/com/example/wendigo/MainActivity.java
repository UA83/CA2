package com.example.wendigo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    private String TAG = "DEBUG";

    // Top of the app from activity_layout
    TextView textViewAppName;
    TextView textViewDate;

    // Middle, where all the magic happens
    ListView listViewTaskContainer;

    private SQLiteDatabase mDatabase;



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

        Log.i(TAG, "getting all: " + db.getAllTasks());

        // Store today's date
        String today = getDate();
        textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(today);

        cursor = db.getAllTasks();

        doListView();

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
                    Log.i(TAG, "Task Inserted: " + insertTask.getTask());

                    // Toast to let the user know that the task was added
                    Toast.makeText(MainActivity.this, "Task: "
                                    + insertTask.getTask() + "Inserted.",
                            Toast.LENGTH_SHORT).show();

                    editTextInsertTask.setText("");
                    closeKeyboard();
                    db.addTask(insertTask);
                    String cc = String.valueOf(cursor.isClosed());
                    Toast.makeText(MainActivity.this, cc, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "adapter from onclick: " + adapter);

                    cursor = db.getAllTasks();
                    doListView();

            }
        });
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Task arrayListDataIntent = new Task(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));

        // Debbuging
        Log.i(TAG, "ID: " + arrayListDataIntent.getID()
                        + " task: " + arrayListDataIntent.getTask()
                        + " Status: " + arrayListDataIntent.getStatus());

        Intent intent = new Intent(MainActivity.this, ManageTaskActivity.class);
        intent.putExtra("List", (Parcelable) arrayListDataIntent);

        // Start the new activity
        startActivity(intent);
    }


    public void doListView(){
        adapter = new TaskAdapter(MainActivity.this, cursor, 0);
        listView = findViewById(android.R.id.list);
        Log.i(TAG, "adapter from on created: " + adapter);
        listView.setAdapter(adapter);
    }

    public String getDate() {
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        return date_n;
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


















