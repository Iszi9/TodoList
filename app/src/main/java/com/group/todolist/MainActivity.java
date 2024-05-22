package com.group.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.group.todolist.Adapter.TodoAdapter;
import com.group.todolist.Model.TodoModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private TodoAdapter tasksAdapter;

    private List<TodoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TodoAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        //yo demo tasks
        TodoModel task = new TodoModel();
        task.setTask("Yo, Demo task straight from Landmark. read on IOT, no copy and paste '@prof Lingo' ");
        task.setStatus(0);
        task.setId(1);

        //yo a static demo task lists
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        //yo display tasks on screen
        tasksAdapter.setTasks(taskList);
    }
}