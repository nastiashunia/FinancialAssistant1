package com.example.financialassistant.UI.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financialassistant.App;
import com.example.financialassistant.DB.DatabaseHelper;
import com.example.financialassistant.DB.Model.Document;
import com.example.myroom1.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddDocumentActivity extends AppCompatActivity {

    @BindView(R.id.name_document)
    EditText name_document;
    @BindView(R.id.date_start_document)
    CalendarView date_start_document;
    @BindView(R.id.date_finish_document)
    CalendarView date_finish_document;

    @BindView(R.id.date_f)
    TextView date_f;
    @BindView(R.id.date_s)
    TextView date_s;

    Boolean flag = false;
    Boolean flag1 = false;
    int count_click = 0;
    int count_click_1 = 0;
    long timeMilliStart;
    long timeMilliFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);
        ButterKnife.bind(this);
        setTitle("Добавить документ");

        date_start_document.setVisibility(View.GONE);
        date_finish_document.setVisibility(View.GONE);

        date_start_document = (CalendarView)findViewById(R.id.date_start_document);

        date_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_click == 0){
                    count_click = 1;
                date_start_document.setVisibility(View.VISIBLE);
                date_start_document.setOnDateChangeListener(new OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;

                String selectedDate = new StringBuilder().append(mDay)
                        .append(".").append(mMonth + 1).append(".").append(mYear)
                        .append(" ").toString();

                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();

                date_start_document.setVisibility(View.GONE);
                Calendar c = Calendar.getInstance();
                c.set(year, month , dayOfMonth, 0 ,0);
                timeMilliStart = c.getTimeInMillis();

                date_s.setText(selectedDate);
                date_s.setVisibility(View.VISIBLE);
                flag = true;
                count_click = 0;
            }});
                }
                else {
                    date_start_document.setVisibility(View.GONE);
                    count_click = 0;
                }
            }
        });

        date_finish_document = (CalendarView)findViewById(R.id.date_finish_document);

        date_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_click_1 == 0){
                    count_click_1 = 1;
                date_finish_document.setVisibility(View.VISIBLE);
                date_finish_document.setOnDateChangeListener(new OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;

                String selectedDate1 = new StringBuilder().append(mDay)
                        .append(".").append(mMonth + 1).append(".").append(mYear)
                        .append(" ").toString();

                Toast.makeText(getApplicationContext(), selectedDate1, Toast.LENGTH_SHORT).show();

                date_finish_document.setVisibility(View.GONE);
                Calendar f = Calendar.getInstance();
                f.set(year, month , dayOfMonth, 0 ,0);
                timeMilliFinish = f.getTimeInMillis();

                date_f.setText(selectedDate1);
                date_f.setVisibility(View.VISIBLE);
                flag1 = true;
                count_click_1 = 0;
            }});
                }
                else {
                    date_finish_document.setVisibility(View.GONE);
                    count_click_1 = 0;
                }
            }
        });
    }


    @OnClick(R.id.save)
    public void onSaveClick() {
        //errorSave();
        String strsumCost = name_document.getText().toString();
        if(TextUtils.isEmpty(strsumCost)) { name_document.setError("Введите наименование документа"); return; }
        if (flag == false ){
            showToast();}
        else {if (flag1 == false ){
            showToast();}
        else if (timeMilliStart > timeMilliFinish)
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Дата начала больше даты окончания срока действия документа!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else
            enter();}
    }

    public void showToast() {
        //создаём и отображаем текстовое уведомление
        Toast toast = Toast.makeText(getApplicationContext(),
                "Выберите дату начала и окончания срока действия документа!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void enter(){
        DatabaseHelper databaseHelper = App.getInstance().getDatabaseInstance();

        Document model = new Document();
        model.name = name_document.getText().toString();
        model.start_date = timeMilliStart;
        model.finish_date = timeMilliFinish;

        databaseHelper.getDocumentDao().insertDocument(model);

        finish();
    }

}
