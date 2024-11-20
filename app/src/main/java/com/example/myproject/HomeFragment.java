package com.example.myproject;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView reminderRecyclerView, habitRecyclerView;
    private ArrayList<ReminderItem> reminderItems = new ArrayList<>();
    private ArrayList<ReminderItem> homeReminders = new ArrayList<>();
    private ArrayList<HabitItem> habitItems = new ArrayList<>();
    private HabitAdapter habitAdapter;
    private ReminderAdapter reminderAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

//    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the RecyclerView
        reminderRecyclerView = view.findViewById(R.id.homeTaskContainer);
        reminderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        habitRecyclerView = view.findViewById(R.id.homeHabitContainer);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get the items from main activity
        if (getArguments() != null) {
            reminderItems = (ArrayList<ReminderItem>) getArguments().getSerializable("data", ArrayList.class);
            habitItems = (ArrayList<HabitItem>) getArguments().getSerializable("data2", ArrayList.class);
        }

        categorizedItem(reminderItems);

        // Set up adapter
        reminderAdapter = new ReminderAdapter(homeReminders);
        reminderRecyclerView.setAdapter(reminderAdapter);

        habitAdapter = new HabitAdapter(habitItems);
        habitRecyclerView.setAdapter(habitAdapter);

        return view;
    }

    private void categorizedItem(ArrayList<ReminderItem> items){
        for(ReminderItem reminder : items){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime formated = LocalDateTime.parse(reminder.getRemainderTime(), formatter);
            LocalDateTime now = LocalDateTime.now();

            if (!reminder.getCheckBox()){
                homeReminders.add(reminder);
            }
        }
    }
}