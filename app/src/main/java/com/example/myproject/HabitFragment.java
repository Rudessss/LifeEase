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

import java.util.ArrayList;

public class HabitFragment extends Fragment {

    private RecyclerView recyclerView;
    private HabitAdapter adapter;
    private ArrayList<HabitItem> items = new ArrayList<>();

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
        return view;
    }
}