package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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