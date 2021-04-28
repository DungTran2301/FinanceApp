package com.example.simpleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {
    private static final String TAG = "AddNewTask";
    private EditText edtContentTask;
    private EditText edtDueDate;
    private Button btSaveTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        edtContentTask = view.findViewById(R.id.task_content);
        edtDueDate = view.findViewById(R.id.task_due_date);
        btSaveTask = view.findViewById(R.id.task_add_button);
    }
}
