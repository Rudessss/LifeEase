package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class InventoryFragment extends Fragment {

    private RecyclerView inventoryGrid;
    private InventoryAdapter adapter;
    private ArrayList<InventoryItem> items = new ArrayList<>();

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

        return view;

    }
}