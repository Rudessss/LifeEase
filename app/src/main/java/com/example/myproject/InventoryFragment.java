package com.example.myproject;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class InventoryFragment extends Fragment {

    private RecyclerView inventoryGrid;
    private InventoryAdapter adapter;
    private ArrayList<InventoryItem> items = new ArrayList<>();
    private Button addNewButton;

    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        // Initialize the RecyclerView
        inventoryGrid = view.findViewById(R.id.recyclerview);
        inventoryGrid.setLayoutManager(new GridLayoutManager(getContext(), 4)); // 4 columns

        // Get the items from main activity
        if (getArguments() != null) {
            items = (ArrayList<InventoryItem>) getArguments().getSerializable("data", ArrayList.class);
        }

        // Set up adapter
        adapter = new InventoryAdapter(items);
        inventoryGrid.setAdapter(adapter);

        addNewButton = view.findViewById(R.id.inventoryAddNew);
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

        final EditText inputName = new EditText(getContext());
        inputName.setHint("Title");
        layout.addView(inputName);

        final EditText inputLocation = new EditText(getContext());
        inputLocation.setHint("Location");
        layout.addView(inputLocation);

//        final EditText inputDescription = new EditText(getContext());
//        inputDescription.setHint("Description");
//        layout.addView(inputDescription);

        builder.setView(layout);

        // Set up dialog buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = inputName.getText().toString();
            String location = inputLocation.getText().toString();

            // Add item with user input
            InventoryItem newItem = new InventoryItem(name, location,R.drawable.ic_baseline_habit_new_24);
            items.add(newItem);
            adapter.notifyItemInserted(items.size() - 1);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}