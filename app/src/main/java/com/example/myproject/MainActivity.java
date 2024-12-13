package com.example.myproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myproject.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    ActivityMainBinding binding;
    ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    ArrayList<ReminderItem> reminderItems = new ArrayList<>();
    ArrayList<HabitItem> habitItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(), reminderItems, habitItems);

        deleteAndRebuildDatabase();
        input_data();
        get_data();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.dashboard) {
                replaceFragment(new HomeFragment(), reminderItems, habitItems);
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

    private void replaceFragment(Fragment fragment, ArrayList<?>... dataLists) {
        if (dataLists != null) {
            Bundle bundle = new Bundle();

            // Add the lists to the bundle with unique keys
            if (dataLists.length > 0 && dataLists[0] != null) {
                bundle.putSerializable("data", dataLists[0]);
            }
            if (dataLists.length > 1 && dataLists[1] != null) {
                bundle.putSerializable("data2", dataLists[1]);
            }

            fragment.setArguments(bundle);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    void input_data(){
//        reminderItems.add(new ReminderItem("High", "Mobile Programing", "24-10-2023 07:20",true));
//        reminderItems.add(new ReminderItem("Medium", "Computer Vision", "24-10-2023 13:20",false));
//        reminderItems.add(new ReminderItem("Medium", "Deep Learning", "24-10-2025 13:20",false));
//        reminderItems.add(new ReminderItem("Low", "Operating System", "24-11-2024 09:20",false));

        inventoryItems.add(new InventoryItem("Cards", "Drawer",R.drawable.ic_baseline_home_24));
        inventoryItems.add(new InventoryItem("Key", "Drawer",R.drawable.ic_baseline_storage_24));

        habitItems.add(new HabitItem(R.drawable.ic_baseline_sports_baseball_24, "Exercise 30 Morning Minutes", "Do 30 minutes morning exercise everyday"));
        habitItems.add(new HabitItem(R.drawable.ic_baseline_search_24, "Read Book 1 Hour", "Focus reading a book for 1 hor straight without any distraction"));

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("priority", "High");
        values1.put("title", "Mobile Programming");
        values1.put("timeDate", "24-10-2023 07:20");
        values1.put("isChecked", 0);
        db.insert("reminders", null, values1);

        ContentValues values2 = new ContentValues();
        values2.put("priority", "Medium");
        values2.put("title", "Deep Learning");
        values2.put("timeDate", "24-10-2023 13:20");
        values2.put("isChecked", 1); // Already completed
        db.insert("reminders", null, values2);

        ContentValues values3 = new ContentValues();
        values3.put("priority", "Low");
        values3.put("title", "Operating System");
        values3.put("timeDate", "24-10-2025 13:20"); // Past date
        values3.put("isChecked", 0);
        db.insert("reminders", null, values3);

        ContentValues values4 = new ContentValues();
        values4.put("priority", "Low");
        values4.put("title", "Computer Vision");
        values4.put("timeDate", "24-10-2023 13:20"); // Past date
        values4.put("isChecked", 0);
        db.insert("reminders", null, values4);

        db.close();
    }

    void get_data(){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

// Example: Get completed reminders
        Cursor cursor = db.query("reminders", null, null, null, null, null, null);


//        ArrayList<ReminderItem> completedReminders = new ArrayList<>();
        for (String columnName : cursor.getColumnNames()) {
            Log.d("CursorColumns:", columnName);
            int columnIndex = cursor.getColumnIndex(columnName);
            Log.d("CursorColumns:", String.valueOf(columnIndex));
        }
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Get the priority value from the cursor
                String priority = cursor.getString(cursor.getColumnIndexOrThrow("priority"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String timeDate = cursor.getString(cursor.getColumnIndexOrThrow("timeDate"));
                boolean isChecked = cursor.getInt(cursor.getColumnIndexOrThrow("isChecked"))==1;

                reminderItems.add(new ReminderItem(priority, title, timeDate, isChecked));
                // Log the priority value
                Log.d("ReminderPriority", priority);
                Log.d("ReminderPriority", title);
                Log.d("ReminderPriority", timeDate);
                Log.d("ReminderPriority", ""+isChecked);
            }
            cursor.close(); // Close the cursor after use
        }
        db.close();
    }

    private void deleteAndRebuildDatabase() {
        // Delete the existing database
        boolean isDeleted = deleteDatabase(DatabaseHelper.DATABASE_NAME);

        if (isDeleted) {
            Log.d("MainActivity", "Database deleted successfully.");
        } else {
            Log.d("MainActivity", "Database deletion failed or database did not exist.");
        }

        // Rebuild the database
        SQLiteDatabase db = databaseHelper.getWritableDatabase(); // Triggers onCreate() if the database doesn't exist
        db.close();
        Log.d("MainActivity", "Database rebuilt successfully.");
    }

}