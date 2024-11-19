package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabUpcomingFragment extends Fragment {

    ArrayList<ReminderItem> items = new ArrayList<>();
    public TabUpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_upcoming, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        items.add(new ReminderItem("High", "Mobile Programing", "07:20",true));
        items.add(new ReminderItem("Medium", "Deep Learning", "13:20",false));
        items.add(new ReminderItem("Low", "Operating System", "15:20",false));


        // Set up RecyclerView with data
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ReminderAdapter(items));

        return view;
    }
}