package com.example.derekwu.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
    }

    public void onSubmit(View v) {
        EditText editField = (EditText) findViewById(R.id.editText);
        Intent data = new Intent();
        data.putExtra("newText", editField.getText().toString());
        data.putExtra("itemPos", editItemPos);
        data.putExtra("code", 200);
        setResult(RESULT_OK, data);
        finish();
    }
}
