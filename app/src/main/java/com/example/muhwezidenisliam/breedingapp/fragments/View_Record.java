package com.example.muhwezidenisliam.breedingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.menu_adapter.Person;
import com.example.muhwezidenisliam.breedingapp.menu_adapter.RVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhwezi Denis Liam on 6/28/2017.
 */

public class View_Record extends Fragment {
    private List<Person> persons;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.recyclerview_activity, container, false);

        persons = new ArrayList<>();
        persons.add(new Person("View Breeding Records", R.drawable.ic_speaker_notes_black_36dp));
        persons.add(new Person("View Cattle Records", R.drawable.ic_speaker_notes_black_36dp));
        persons.add(new Person("View Heifer Records", R.drawable.ic_speaker_notes_black_36dp));
        persons.add(new Person("View Milk Records", R.drawable.ic_speaker_notes_black_36dp));

        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =new RVAdapter(persons,getActivity());
        recyclerView.setAdapter(adapter);


        return  view;
    }
}
