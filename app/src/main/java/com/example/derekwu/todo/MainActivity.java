package com.example.derekwu.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> items;
    ArrayAdapter<Task> itemsAdapter;
    ListView lvItems;
    private final int REQUEST_CODE = 20; //Edit Task

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList(new Select().from(Task.class).limit(100).execute());
        itemsAdapter = new TasksAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListEventListener();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String newText = data.getExtras().getString("newText");
            Date newDate = new Date(data.getExtras().getString("newDate"));
            int itemPos = data.getExtras().getInt("itemPos");
            Task edittedTask = items.get(itemPos);
            edittedTask.name = newText;
            edittedTask.date = newDate;
            edittedTask.save();
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Updated Task " + newDate.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setupListEventListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id){
                Log.d("ITEM",items.toString());
                editItem(items.get(pos), pos);
                return true;
            }
        });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        Task newTask = new Task(itemText);
        newTask.save();
        itemsAdapter.add(newTask);
        itemsAdapter.notifyDataSetChanged();
        etNewItem.setText("");
    }

    public void editItem(Task taskItem, int itemPos) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("itemText", taskItem.name);
        i.putExtra("itemDate", taskItem.date.toString());
        i.putExtra("itemPos", itemPos);
        startActivityForResult(i, REQUEST_CODE);
    }
}


