package com.example.financialassistant.DB.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
        //(foreignKeys = @ForeignKey(entity = CategoryIncome.class, parentColumns = "id", childColumns = "categoryincome_id" ))
public class Income {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String comment;

    public int sum;

    public long date;

    @ColumnInfo(name = "categoryincome_id")
    public long categoryIncomeId;

    @ColumnInfo(name = "document_id")
    public long documentId;

}



