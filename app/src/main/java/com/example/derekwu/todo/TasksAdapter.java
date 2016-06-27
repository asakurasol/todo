package com.example.derekwu.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Derekwu on 6/27/16.
 */

public class TasksAdapter extends ArrayAdapter<Task> {
    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.taskitem, parent, false);
        }
        // Lookup view for data population
        TextView taskName = (TextView) convertView.findViewById(R.id.taskName);
        TextView taskDate = (TextView) convertView.findViewById(R.id.taskDate);
        // Populate the data into the template view using the data object
        taskName.setText(task.name);
        if (task.date == null) {
            task.date = new Date();
            task.save();
        }
        taskDate.setText("Deadline: " + new SimpleDateFormat("MM-dd-yyyy").format(task.date));
        // Return the completed view to render on screen
        return convertView;
    }
}
