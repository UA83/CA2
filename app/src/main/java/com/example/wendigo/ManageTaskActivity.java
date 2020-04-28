package com.example.wendigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageTaskActivity extends AppCompatActivity {

    String TAG = "INFO";

    Button btnDone;
    Button btnDelete;
    Button btnUpdate;

    EditText editTextUpdateTask;

    TasksDatabaseHandler mDatabase = new TasksDatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_task);

        editTextUpdateTask = findViewById(R.id.edit_text_update_task);

        Intent intent = getIntent();
        String taskName = intent.getStringExtra("taskName");
        String taskStatus = intent.getStringExtra("taskStatus");
        String taskID = intent.getStringExtra("taskID");

        editTextUpdateTask.setText(taskName);


        //***********************************************
        //***********************************************
        // DELETED
        btnDelete = findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageTaskActivity.this, "Task DELETED: ", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Task DELETED: ");
            }
        });

        //***********************************************
        //***********************************************
        // DONE
        btnDone = findViewById(R.id.button_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageTaskActivity.this, "Task DONE: ", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Task DONE: ");
            }
        });


        //***********************************************
        //***********************************************
        // UPDATE
        btnUpdate = findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageTaskActivity.this, "Task UPDATED: ", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Task UPDATED: ");
              }
        });


    }

}
