package com.example.govbondapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class UserList extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> name, email, password;
    DBHelper DB;
    MyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        DB = new DBHelper(getActivity());
        name = new ArrayList<>();
        email = new ArrayList<>();
        password = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mAdapter = new MyAdapter(getActivity(), name, email, password);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        displayData();

        return view;
    }

    private void displayData() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No users exist", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(1));
                email.add(cursor.getString(0));
                password.add(cursor.getString(2));
            }
        }
    }
}