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

        // Initialize inventory items (populate with example items or load dynamically)
        items.add(new InventoryItem("Cards", "Drawer",R.drawable.ic_baseline_home_24));
        items.add(new InventoryItem("Key", "Drawer",R.drawable.ic_baseline_storage_24));
        // Add more items as needed

        // Set up adapter
        adapter = new InventoryAdapter(items);
        inventoryGrid.setAdapter(adapter);

        return view;

    }
}