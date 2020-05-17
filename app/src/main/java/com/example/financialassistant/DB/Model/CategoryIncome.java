package com.example.financialassistant.DB.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryIncome {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public static final String[] CATEGORY_START_NAME ={
            "Зарплата", "Стипендия"};
    /*CategoryIncome(long id ,String name){
        this.id = id;
        this.name = name;
    }*/
}