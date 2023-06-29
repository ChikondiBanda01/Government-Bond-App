package com.example.govbondapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CostFragment extends Fragment {

    private Button calculateCostBtn;
    EditText faceValue;
    EditText bondPrice;
    TextView cost;
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cost, container, false);
        
        calculateCostBtn = view.findViewById(R.id.calculateCostBtn);
        faceValue = view.findViewById(R.id.faceValue);
        bondPrice = view.findViewById(R.id.bondPrice);
        cost = view.findViewById(R.id.txtCost);
        mTextView = view.findViewById(R.id.textView4);
        
        calculateCostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                
                String getFaceValue = faceValue.getText().toString();
                Double fv = Double.parseDouble(getFaceValue);

                String getBondPrice = bondPrice.getText().toString();
                Double bp = Double.parseDouble(getBondPrice);

                if (getBondPrice.isEmpty() || getFaceValue.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all values", Toast.LENGTH_SHORT).show();
                } else {
                    cost.setText("K" + calculateCost(fv, bp));
                    mTextView.setText("Bond Cost");
                }
            }
        });
        
        
        return view;
    }

    private Double calculateCost(Double fv, Double bp) {
        double cost = (fv * bp) / 100;
        cost = Math.round(cost * 100.0)/100.0;
        return cost;
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imn = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
