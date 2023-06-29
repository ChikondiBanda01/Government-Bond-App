package com.example.govbondapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageUsers extends Fragment {

    EditText name, email, password;
    Button addBtn, viewBtn;
    DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_users, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        addBtn = view.findViewById(R.id.addUser);
        viewBtn = view.findViewById(R.id.viewUser);

        //Initialise DB Helper
        DB = new DBHelper(getActivity());

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment userList = new UserList();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_view, userList);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String nameTxt = name.getText().toString();
                String passwordTxt = password.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(emailTxt, nameTxt, passwordTxt);
                if (checkinsertdata = true) {
                    Toast.makeText(getActivity(), "New User Added", Toast.LENGTH_SHORT).show();

                    emptyTextFields();

                } else {
                    Toast.makeText(getActivity(), "Please Enter All Required Details", Toast.LENGTH_SHORT).show();
                }

                closeKeyboard();

            }
        });

        return view;


    }

    private void emptyTextFields() {
        name.setText("");
        email.setText("");
        password.setText("");
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imn = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}