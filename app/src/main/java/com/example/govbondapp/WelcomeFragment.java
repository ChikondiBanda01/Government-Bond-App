package com.example.govbondapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Stack;

public class WelcomeFragment extends Fragment {
    TextView user;
    DBHelper DB;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_main_page, container, false);

        user = view.findViewById(R.id.userType);
        DB = new DBHelper(getActivity());

        // Gets the data from the passed bundle
        String usertype = getArguments().getString("USERTYPE");
        String emailAddress = getArguments().getString("EMAIL");

        if (usertype.equals("investor")) {
            user.setText("Investor");
        }

        return view;
    }

}
