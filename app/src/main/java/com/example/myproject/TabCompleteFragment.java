package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabCompleteFragment extends Fragment{

    ArrayList<ReminderItem> items = new ArrayList<>();

    public TabCompleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_complete, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        if (getArguments() != null) {
            items = (ArrayList<ReminderItem>) getArguments().getSerializable("reminders", ArrayList.class);
        }

        // Set up RecyclerView with data
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ReminderAdapter(items));

        return view;
    }
}