package com.example.todolist;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    ArrayList<String> taskList;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        // Initialize task list
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        // Handle FAB click to open the fragment for input
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                TaskInputFragment inputFragment = new TaskInputFragment();
                inputFragment.show(fragmentManager, "TaskInputFragment");
            }
        });
    }

    // Method to add task to the list
    public void addTask(String task) {
        taskList.add(task);
        taskAdapter.notifyDataSetChanged();
    }
}