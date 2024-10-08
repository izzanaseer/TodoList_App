package com.example.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaskInputFragment  extends Fragment {

    private EditText etTaskName, etTaskDescription;
    private Button btnSaveTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);

        // Initialize the views
        etTaskName = view.findViewById(R.id.etTaskName);
        etTaskDescription = view.findViewById(R.id.etTaskDescription);
        btnSaveTask = view.findViewById(R.id.btnSaveTask);

        // Handle the save button click
        btnSaveTask.setOnClickListener(v -> {
            String taskName = etTaskName.getText().toString();
            String taskDescription = etTaskDescription.getText().toString();

            if (taskName.isEmpty()) {
                Toast.makeText(getActivity(), "Task name is required", Toast.LENGTH_SHORT).show();
            } else {
                // Use a callback to pass the task to the main activity
                if (getActivity() instanceof TaskInputListener) {
                    ((TaskInputListener) getActivity()).onTaskInput(taskName, taskDescription);
                }

                // Close the fragment after saving
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    // Define an interface to communicate with the activity
    public interface TaskInputListener {
        void onTaskInput(String taskName, String taskDescription);
    }
}