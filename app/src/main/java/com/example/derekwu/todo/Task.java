package com.example.derekwu.todo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "Tasks")
public class Task extends Model {
    // This is a regular field
    @Column(name = "Name")
    public String name;

    @Column(name = "Date")
    public Date date;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Task(){
        super();
    }

    public Task(String name){
        super();
        this.name = name;
        Date today = new Date();
        this.date = new Date(today.getTime() + (1000 * 60 * 60 * 24));
    }
};