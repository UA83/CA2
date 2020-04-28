package com.example.wendigo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Task> {

    public WordAdapter(@NonNull Context context, @NonNull ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.row, parent, false);
        }

        Task currentWord = getItem(position);

        TextView taskTask = listItemView.findViewById(R.id.text_view_task_name);
        taskTask.setText(currentWord.getTask());

        return listItemView;
    }
}