package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.TaskViewHolder> {
    private final List<ReminderItem> taskList;

    public ReminderAdapter(List<ReminderItem> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_reminder, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        ReminderItem task = taskList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView remainderPriotiry, remainderTitle, remainderTime;
        CheckBox checkBox;

        public TaskViewHolder( View itemView) {
            super(itemView);
            remainderPriotiry = itemView.findViewById(R.id.remainderPriority);
            remainderTitle = itemView.findViewById(R.id.remainderTitle);
            remainderTime = itemView.findViewById(R.id.remainderTime);
            checkBox = itemView.findViewById(R.id.reminderCheckBox);
        }

        public void bind(ReminderItem task) {
            remainderPriotiry.setText(task.getRemainderPriotiry());
            remainderTitle.setText(task.getRemainderTitle());
            remainderTime.setText(task.getRemainderTime());
            checkBox.setChecked(task.getCheckBox());
        }
    }
}

