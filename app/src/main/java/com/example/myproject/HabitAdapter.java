package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {

    private ArrayList<HabitItem> habitItems;

    public HabitAdapter(ArrayList<HabitItem> habitItems) {
        this.habitItems = habitItems;
    }

    public HabitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_habit, parent, false);
        return new HabitAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(HabitAdapter.ViewHolder holder, int position) {
        HabitItem item = habitItems.get(position);
        holder.habitTitle.setText(item.getHabitTitle());
        holder.habitIcon.setImageResource(item.getHabitIcon());
        holder.habitDescription.setText(String.valueOf(item.getHabitDescription()));
    }

    public int getItemCount() {
        return habitItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView habitTitle;
        ImageView habitIcon;
        TextView habitDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            habitTitle = itemView.findViewById(R.id.habitTitle);
            habitIcon = itemView.findViewById(R.id.habitIcon);
            habitDescription = itemView.findViewById(R.id.habitDescription);
        }
    }
}
