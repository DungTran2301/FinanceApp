package com.example.simpleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.GoodsDatabase;
import com.example.simpleapp.database.Tasks;
import com.example.simpleapp.database.TasksDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {
    private EditText edtContentTask, edtDueDate;
    private Button btAddTask;
    private TasksAdapter tasksAdapter;
    private List<Tasks> listTasks;
    private Date dueDate;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initUiAddTask();
        tasksAdapter = new TasksAdapter();
        listTasks = new ArrayList<>();
        listTasks = TasksDatabase.getInstance(this).tasksDAO().getListTasks();
        tasksAdapter.setData(listTasks);



        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edtDueDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddTaskActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addTasks() == -1) {
                    Toast.makeText(AddTaskActivity.this, "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_LONG).show();
                }
            }
        });


        Button backFromAddTask = findViewById(R.id.button_back_add_task);
        backFromAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, AddSpend.class);
                startActivity(intent);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd/MM/YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtDueDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void initUiAddTask() {
        edtContentTask = findViewById(R.id.edt_task_content);
        edtDueDate = findViewById(R.id.edt_task_due_date);
        btAddTask = findViewById(R.id.bt_task_add_button);
    }
    private int addTasks() {
        String content = edtContentTask.getText().toString().trim();
        String dueDate = edtDueDate.getText().toString().trim();

        if (content.equals("") || dueDate.equals("")) {
            return -1;
        }
        Tasks tasks = new Tasks(content, dueDate, false);
        TasksDatabase.getInstance(this).tasksDAO().insertTasks(tasks);
        Toast.makeText(this, "Thêm nhiệm vụ thành công!", Toast.LENGTH_LONG).show();

        edtContentTask.setText("");
        edtDueDate.setText("");
        listTasks = TasksDatabase.getInstance(this).tasksDAO().getListTasks();
        tasksAdapter.setData(listTasks);
        return 1;
    }

}