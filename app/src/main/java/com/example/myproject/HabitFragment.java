package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class HabitFragment extends Fragment {

    private RecyclerView recyclerView;
    private HabitAdapter adapter;
    private ArrayList<HabitItem> items = new ArrayList<>();
    private Button addNewButton;

    public HabitFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // 4 columns

        // Get the items from main activity
        if (getArguments() != null) {
            items = (ArrayList<HabitItem>) getArguments().getSerializable("data", ArrayList.class);
        }

        adapter = new HabitAdapter(items);
        recyclerView.setAdapter(adapter);

        addNewButton = view.findViewById(R.id.habitAddNew);
        addNewButton.setOnClickListener(v -> addItem());

        return view;
    }

    private void addItem() {
        // Use an AlertDialog for user input
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add New Item");

        // Set up input fields
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText habitTitle = new EditText(getContext());
        habitTitle.setHint("What new Habit you wanna build");
        layout.addView(habitTitle);

        final EditText habitDescription = new EditText(getContext());
        habitDescription.setHint("Description");
        layout.addView(habitDescription);

        builder.setView(layout);

        // Set up dialog buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            String title = habitTitle.getText().toString();
            String description = habitDescription.getText().toString();

            // Add item with user input
            HabitItem newItem = new HabitItem(R.drawable.ic_baseline_habit_new_24, title, description);
            items.add(newItem);
            adapter.notifyItemInserted(items.size() - 1);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

}