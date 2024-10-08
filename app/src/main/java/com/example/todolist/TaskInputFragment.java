package com.example.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TaskInputFragment  extends DialogFragment {

    EditText etTaskInput;
    Button btnAddTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);

        etTaskInput = view.findViewById(R.id.etTaskInput);
        btnAddTask = view.findViewById(R.id.btnAddTask);

        // Handle button click to add the task
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTaskInput.getText().toString();
                if (!task.isEmpty()) {
                    ((Dashboard) getActivity()).addTask(task);
                    dismiss();
                }
            }
        });

        return view;
    }
}