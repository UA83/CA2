package com.example.wendigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageTaskActivity extends AppCompatActivity {

    Button btnDone;
    Button btnDelete;
    Button btnUpdate;

    EditText editTextUpdateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_task);

        editTextUpdateTask = findViewById(R.id.edit_text_update_task);

        Intent intent = getIntent();
        String taskName = intent.getStringExtra("taskName");

        editTextUpdateTask.setText(taskName);

        btnDelete = findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDelete();
            }
        });

        btnDone = findViewById(R.id.button_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDone();
            }
        });

        btnUpdate = findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskUpdate();
            }
        });


    }

    private void taskDelete() {
        Toast.makeText(this, "This task Was DELETED", Toast.LENGTH_SHORT).show();
    }

    private void taskUpdate() {
        Toast.makeText(this, "This task was UPDATED", Toast.LENGTH_SHORT).show();
    }

    private void taskDone() {
        Toast.makeText(this, "This task is DONE now", Toast.LENGTH_SHORT).show();
    }


}
