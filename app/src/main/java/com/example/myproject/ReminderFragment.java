package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReminderFragment extends Fragment {

    public ReminderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.reminderTabMenu);

        // Load default fragment (e.g., UpcomingFragment)
        replaceFragment(new TabUpcomingFragment());

        // Handle navigation item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.upcomingTab) {
                replaceFragment(new TabUpcomingFragment());
            } else if (itemId == R.id.overdueTab) {
                replaceFragment(new TabOverdueFragment());
            } else if (itemId == R.id.completeTab) {
                replaceFragment(new TabCompleteFragment());
            }
            return true;
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.reminderTabs, fragment);
        transaction.commit();
    }
}