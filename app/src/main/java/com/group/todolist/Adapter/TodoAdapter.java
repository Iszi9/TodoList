package com.group.todolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.group.todolist.MainActivity;
import com.group.todolist.Model.TodoModel;
import com.group.todolist.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoModel> todolist;
    private MainActivity activity;

    public TodoAdapter(MainActivity activity){
        this.activity = activity;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new RecyclerView.ViewHolder(itemView);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }

}
