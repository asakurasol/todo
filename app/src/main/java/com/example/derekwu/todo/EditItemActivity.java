package com.example.derekwu.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class EditItemActivity extends AppCompatActivity {

    int editItemPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String editItemText = getIntent().getStringExtra("itemText");
        editItemPos = getIntent().getIntExtra("itemPos", 1);
        EditText editField = (EditText) findViewById(R.id.editText);
        editField.setText(editItemText);
        //set date
        Date editItemDate = new Date(getIntent().getStringExtra("itemDate"));
        setTime(editItemDate);
    }

    public void setTime(Date editItemDate){
        DatePicker dtPicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        Log.d("DATE", editItemDate.toString());
        cal.setTime(editItemDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dtPicker.updateDate(year, month, day);
    }

    public Date getTime(){
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public void onSubmit(View v) {
        EditText editField = (EditText) findViewById(R.id.editText);
        Intent data = new Intent();
        data.putExtra("newText", editField.getText().toString());
        data.putExtra("itemPos", editItemPos);
        data.putExtra("newDate", getTime().toString());
        data.putExtra("code", 200);
        setResult(RESULT_OK, data);
        finish();
    }
}
