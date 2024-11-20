package com.example.myproject;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReminderFragment extends Fragment {

    public ReminderFragment() {
        // Required empty public constructor
    }

    ArrayList<ReminderItem> items = new ArrayList<>();
    ArrayList<ReminderItem> completedReminders = new ArrayList<>();
    ArrayList<ReminderItem> upcomingReminders = new ArrayList<>();
    ArrayList<ReminderItem> overdueReminders = new ArrayList<>();

    Button reminderAddNew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.reminderTabMenu);

        // Get the items from main activity
        if (getArguments() != null) {
            items = (ArrayList<ReminderItem>) getArguments().getSerializable("data", ArrayList.class);
        }

        categorizedItem(items);

        // Load default fragment (e.g., UpcomingFragment)
        replaceFragment(new TabUpcomingFragment(), upcomingReminders);

        // Handle navigation item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.upcomingTab) {
                replaceFragment(new TabUpcomingFragment(), upcomingReminders);
            } else if (itemId == R.id.overdueTab) {
                replaceFragment(new TabOverdueFragment(), overdueReminders);
            } else if (itemId == R.id.completeTab) {
                replaceFragment(new TabCompleteFragment(), completedReminders);
            }
            return true;
        });

        reminderAddNew = view.findViewById(R.id.reminderAddNew);
        reminderAddNew.setOnClickListener(v -> addItem());

        return view;
    }

    private void categorizedItem(ArrayList<ReminderItem> items){
        for(ReminderItem reminder : items){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime formated = LocalDateTime.parse(reminder.getRemainderTime(), formatter);
            LocalDateTime now = LocalDateTime.now();

            if (reminder.getCheckBox()){
               completedReminders.add(reminder);
            } else if (formated.isAfter(now)) {
                upcomingReminders.add(reminder);
            } else if (formated.isBefore(now)) {
                overdueReminders.add(reminder);
            }
        }
    }

    private void addItem() {
        // Use an AlertDialog to get user input for the task
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Task");

        // Set up a layout for user input
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        // Task title input
        final EditText priorityInput = new EditText(getContext());
        priorityInput.setHint("Task Priority");
        layout.addView(priorityInput);

        // Task description input
        final EditText titleInput = new EditText(getContext());
        titleInput.setHint("Task Title");
        layout.addView(titleInput);

        final EditText timeInput = new EditText(getContext());
        timeInput.setHint("dd-MM-yyyy HH:mm");
        layout.addView(timeInput);

        builder.setView(layout);

        // Add buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            String description = priorityInput.getText().toString().trim();
            String title = titleInput.getText().toString().trim();
            String time = timeInput.getText().toString().trim();

            if (!title.isEmpty()) {
                // Add new task to the ArrayList
                ReminderItem newItem = new ReminderItem(title, description, time,false);
                items.add(newItem);

                // Optionally update the UI or notify the adapter
                Toast.makeText(requireContext(), "Task added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Title cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
    
    private void replaceFragment(Fragment fragment, ArrayList<ReminderItem> reminders) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("reminders", reminders);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.reminderTabs, fragment);
        transaction.commit();
    }
}