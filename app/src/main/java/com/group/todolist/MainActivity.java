package com.group.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.todolist.Adapter.RecyclerViewAdapter;
import com.group.todolist.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private EditText addTaskBox;
    private DatabaseReference databaseReference;
    private List<Task> allTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allTask = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        addTaskBox = findViewById(R.id.add_task_box);
        recyclerView = findViewById(R.id.task_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, allTask);
        recyclerView.setAdapter(recyclerViewAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                taskDeletion(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void addTask(View view) {
        String enteredTask = addTaskBox.getText().toString();
        if (TextUtils.isEmpty(enteredTask)) {
            Toast.makeText(MainActivity.this, "You must enter a task", Toast.LENGTH_LONG).show();
            return;
        }

        Task taskObject = new Task(enteredTask);
        databaseReference.push().setValue(taskObject);
        addTaskBox.setText("");
    }

    private void getAllTask(DataSnapshot dataSnapshot) {
        String taskTitle = dataSnapshot.getValue(String.class);
        allTask.add(new Task(taskTitle));
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void taskDeletion(DataSnapshot dataSnapshot) {
        String taskTitle = dataSnapshot.getValue(String.class);
        for (int i = 0; i < allTask.size(); i++) {
            if (allTask.get(i).getTask().equals(taskTitle)) {
                allTask.remove(i);
                break;
            }
        }
        Log.d(TAG, "Task deleted " + taskTitle);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
