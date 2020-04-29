package com.example.wendigo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageTaskActivity extends AppCompatActivity {

    private String TAG = "DEBUG";

    Button btnDone;
    Button btnDelete;
    Button btnUpdate;

    EditText editTextUpdateTask;

    Task taskFromClick;

    TasksDatabaseHandler mDatabase = new TasksDatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_task);

        editTextUpdateTask = findViewById(R.id.edit_text_update_task);

        Intent intent = getIntent();
        taskFromClick  = (Task) intent.getParcelableExtra("List");

        editTextUpdateTask.setText(taskFromClick.getTask());


        //***********************************************
        //***********************************************
        // DELETED
        btnDelete = findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Task DELETED: " + taskFromClick.getTask());
                mDatabase.deleteTask(taskFromClick);
                Log.i(TAG, "Delete Task");
                Toast.makeText(ManageTaskActivity.this, "Task " + taskFromClick.getTask()+ "DELETED", Toast.LENGTH_SHORT).show();
                Intent backToMain = new Intent(ManageTaskActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });


        //***********************************************
        //***********************************************
        // DONE
        btnDone = findViewById(R.id.button_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Task DONE Before Change: " + taskFromClick.getTask());
                // When button Done is clicked, the status will change to 1, and it will be
                // displayed in the bottom of the list
                taskFromClick.setStatus("1");
                mDatabase.taskUpdate(taskFromClick);
                Log.i(TAG, "Task UPDATED After Change: " + taskFromClick.getTask());
                Toast.makeText(ManageTaskActivity.this, "Task UPDATED", Toast.LENGTH_LONG).show();
                //closeKeyboard();
                Intent backToMain = new Intent(ManageTaskActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });


        //***********************************************
        //***********************************************
        // UPDATE
        btnUpdate = findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Task UPDATED Before Change: " + taskFromClick.getTask());
                editTextUpdateTask = findViewById(R.id.edit_text_update_task);
                taskFromClick.setTask(editTextUpdateTask.getText().toString());
                Log.i(TAG, "Task UPDATED After Change: " + taskFromClick.getTask());
                mDatabase.taskUpdate(taskFromClick);
                Toast.makeText(ManageTaskActivity.this, "Task UPDATED", Toast.LENGTH_LONG).show();
                //closeKeyboard();
                Intent backToMain = new Intent(ManageTaskActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });


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
