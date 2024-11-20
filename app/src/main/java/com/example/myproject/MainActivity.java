package com.example.myproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myproject.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    ArrayList<ReminderItem> reminderItems = new ArrayList<>();
    ArrayList<HabitItem> habitItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(), null);

        input_data();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.dashboard) {
                replaceFragment(new HomeFragment(), null);
            } else if (itemId == R.id.habits) {
                replaceFragment(new HabitFragment(), habitItems);
            } else if (itemId == R.id.inventory) {
                replaceFragment(new InventoryFragment(), inventoryItems);
            } else if (itemId == R.id.remainder) {
                replaceFragment(new ReminderFragment(), reminderItems);
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment, Serializable data){
        if (data != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data); // Pass the appropriate ArrayList
            fragment.setArguments(bundle);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    void input_data(){
        reminderItems.add(new ReminderItem("High", "Mobile Programing", "24-10-2023 07:20",true));
        reminderItems.add(new ReminderItem("Medium", "Computer Vision", "24-10-2023 13:20",false));
        reminderItems.add(new ReminderItem("Medium", "Deep Learning", "24-10-2025 13:20",false));
        reminderItems.add(new ReminderItem("Low", "Operating System", "24-11-2024 09:20",false));

        inventoryItems.add(new InventoryItem("Cards", "Drawer",R.drawable.ic_baseline_home_24));
        inventoryItems.add(new InventoryItem("Key", "Drawer",R.drawable.ic_baseline_storage_24));

        habitItems.add(new HabitItem(R.drawable.ic_baseline_habit_new_24, "Exercise 30 Morning Minutes", "Do 30 minutes morning exercise everyday"));
        habitItems.add(new HabitItem(R.drawable.ic_baseline_search_24, "Read Book 1 Hour", "Focus reading a book for 1 hor straight without any distraction"));

    }

}